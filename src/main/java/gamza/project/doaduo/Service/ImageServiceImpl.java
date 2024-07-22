package gamza.project.doaduo.Service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import gamza.project.doaduo.Repository.ImageRepository;
import gamza.project.doaduo.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

  public void uploadFile(MultipartFile image, String userEmail) {
    String originalFileName = image.getOriginalFilename();
    String fileName = changeFileName(originalFileName);

    try {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(image.getSize());
      metadata.setContentType(image.getContentType());
      metadata.setSSEAlgorithm(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION); // 서버 측 암호화 설정
      metadata.addUserMetadata("uploaded-by",userEmail);

      PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, image.getInputStream(), metadata);
      amazonS3Client.putObject(putObjectRequest);
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("파일 업로드 실패");
    }

    String fileUrl = amazonS3Client.getUrl(bucketName, fileName).toString();

    // ImageEntity 객체를 생성합니다.
    ImageEntity imageEntity = ImageEntity.builder()
        .fileName(fileName)
        .fileUrl(fileUrl)
        .email(userEmail) // 사용자 이메일도 저장할 수 있음
        .build();

    imageRepository.save(imageEntity);
  }


  public void deleteFile(Long imageId) {
    // 데이터베이스에서 이미지 엔티티를 조회합니다.
    ImageEntity imageEntity = imageRepository.findById(imageId)
        .orElseThrow(() -> new RuntimeException("이미지를 찾을 수 없습니다."));

    // S3에서 파일을 삭제합니다.
    amazonS3Client.deleteObject(bucketName, imageEntity.getFileName());

    // 데이터베이스에서 이미지 엔티티를 삭제합니다.
    imageRepository.delete(imageEntity);
  }

  private String changeFileName(String originalFileName) {
    /* 업로드할 파일의 이름을 변경하는 로직 */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    return originalFileName + "_" + LocalDateTime.now().format(formatter);
  }

}