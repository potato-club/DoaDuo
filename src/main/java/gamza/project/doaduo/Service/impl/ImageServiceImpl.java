package gamza.project.doaduo.Service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import gamza.project.doaduo.Repository.ImageRepository;
import gamza.project.doaduo.Service.inter.ImageService;
import gamza.project.doaduo.dto.ImageRequest;
import gamza.project.doaduo.dto.ImageUpdate;
import gamza.project.doaduo.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

  private final AmazonS3Client amazonS3Client;
  private final ImageRepository imageRepository;
  private final String bucketName;

  @Autowired
  public ImageServiceImpl(AmazonS3Client amazonS3Client, ImageRepository imageRepository, @Value("${cloud.aws.s3.bucket}") String bucketName) {
    this.amazonS3Client = amazonS3Client;
    this.imageRepository = imageRepository;
    this.bucketName = bucketName;
  }

  @Override
  public void uploadFile(MultipartFile image, ImageRequest imageRequest) throws IOException {
    String key = image.getOriginalFilename();
    String fileName = UUID.randomUUID() + "-" + key;

    if(amazonS3Client.doesObjectExist(bucketName, key)){
        throw new RuntimeException("이미 존재하는 이미지입니다.");
    }

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(image.getSize());
    amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, image.getInputStream(), metadata));

    // ImageEntity 객체를 생성합니다.
    ImageEntity imageEntity = ImageEntity.builder()
        .originalFileName(key)
        .fileName(fileName)
        .fileUrl(amazonS3Client.getUrl(bucketName, fileName).toString())
        .userEmail(imageRequest.getUser_email()) // 사용자 이메일도 저장할 수 있음
        .build();

    imageRepository.save(imageEntity);
    System.out.println("저장 완료");
  }

  @Override
  public void updateFile(MultipartFile image, ImageUpdate update) throws IOException {
    if (image.isEmpty()) {
      throw new RuntimeException("변경할 이미지가 없습니다.");
    }
    ImageEntity imageEntity  = imageRepository.findByUserEmail(update.getUserEmail());

    if (imageEntity==null) {
      throw new RuntimeException("해당 사용자 이메일로 등록된 프로필 이미지가 없습니다.");
    }

    // S3에서 기존 파일을 삭제합니다.
    amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, update.getFileName()));

    // 새로운 파일을 S3에 업로드합니다.
    String key = image.getOriginalFilename();
    String fileName = UUID.randomUUID() + "-" + key;

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(image.getSize());
    amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, image.getInputStream(), metadata));

    // 기존 이미지 엔티티를 업데이트합니다.
    ImageEntity updatedImageEntity = ImageEntity.builder()
        .id(imageEntity.getId())
        .originalFileName(key)
        .fileName(fileName)
        .fileUrl(amazonS3Client.getUrl(bucketName, fileName).toString())
        .userEmail(imageEntity.getUserEmail())
        .build();
    imageRepository.save(updatedImageEntity);
  }

  @Override
  public void deleteFile(ImageRequest request) throws IOException {
    ImageEntity imageEntity = imageRepository.findByUserEmail(request.getUser_email());

    if (imageEntity==null) {
      throw new RuntimeException("해당 사용자 이메일로 등록된 프로필 이미지가 없습니다.");
    }
    // S3에서 파일을 삭제합니다.
    amazonS3Client.deleteObject(new DeleteObjectRequest(bucketName, imageEntity.getFileName()));


    // 데이터베이스에서 이미지 엔티티를 삭제합니다.
    imageRepository.delete(imageEntity);
  }

  @Override
  public void viewFile(ImageRequest request) throws IOException {

  }


}