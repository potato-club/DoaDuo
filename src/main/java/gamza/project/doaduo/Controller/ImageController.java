package gamza.project.doaduo.Controller;

import gamza.project.doaduo.Service.ImageServiceImpl;
import gamza.project.doaduo.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("Image")
public class ImageController {


  private final ImageServiceImpl imageServiceImpl;

  @Autowired
  public ImageController(ImageServiceImpl imageServiceImpl) {
    this.imageServiceImpl = imageServiceImpl;
  }

  @PostMapping("/uploadImage")
  public ResponseEntity<?> uploadProfilePhoto() {
    return ResponseEntity.ok("");
  }

  @GetMapping("updateImage")
  public ResponseEntity<?> updateImage(@RequestParam("file") MultipartFile file) {
    return ResponseEntity.ok("");
  }



  @DeleteMapping("/deleteImage")
  public ResponseEntity<String> deleteProfilePhoto() {
    return ResponseEntity.ok("파일이 삭제되었습니다.");
  }


}
