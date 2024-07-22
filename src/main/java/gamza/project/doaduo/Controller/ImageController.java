package gamza.project.doaduo.Controller;

import gamza.project.doaduo.Repository.ImageRepository;
import gamza.project.doaduo.Service.ImageServiceImpl;
import gamza.project.doaduo.dto.ImageRequest;
import gamza.project.doaduo.dto.ImageResponse;
import gamza.project.doaduo.dto.ImageUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/Image")
public class ImageController {


  private final ImageServiceImpl imageServiceImpl;

  @Autowired
  public ImageController(ImageServiceImpl imageServiceImpl) {
    this.imageServiceImpl = imageServiceImpl;
  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadProfilePhoto(@RequestPart("file") MultipartFile file, ImageRequest request) throws IOException {
    imageServiceImpl.uploadFile(file, request);
    return ResponseEntity.ok("이미지가 성공적으로 업로드되었습니다.");
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateImage(@RequestParam("file") MultipartFile file, ImageUpdate update) throws IOException {
    imageServiceImpl.updateFile(file, update);
    return ResponseEntity.ok("이미지가 성공적으로 업데이트되었습니다.");
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteProfilePhoto(ImageRequest request) throws IOException {
    imageServiceImpl.deleteFile(request);
    return ResponseEntity.ok("이미지가 성공적으로 업데이트되었습니다.");
  }

//  @GetMapping(value = "/view")
//  public ImageResponse viewProfilePhoto() {
//    return fileService.viewFileList(requestDto);
//  }
}
