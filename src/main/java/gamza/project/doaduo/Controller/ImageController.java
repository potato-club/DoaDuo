package gamza.project.doaduo.Controller;

import gamza.project.doaduo.Service.inter.ImageService;
import gamza.project.doaduo.dto.ImageRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/Image")
public class ImageController {


  private final ImageService imageService;

  @Autowired
  public ImageController(ImageService imageService) {
    this.imageService = imageService;
  }

  @PostMapping("/upload")
  public ResponseEntity<String> uploadProfilePhoto(@RequestPart("file") MultipartFile file, HttpServletRequest request) throws IOException {
    imageService.uploadFile(file, request.getHeader("AT"));
    return ResponseEntity.ok("이미지가 성공적으로 업로드되었습니다.");
  }

  @PutMapping("/update")
  public ResponseEntity<String> updateImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
    imageService.updateFile(file, request.getHeader("AT"));
    return ResponseEntity.ok("이미지가 성공적으로 업데이트되었습니다.");
  }

  @DeleteMapping("/delete")
  public ResponseEntity<String> deleteProfilePhoto(HttpServletRequest request) throws IOException {
    imageService.deleteFile(request.getHeader("AT"));
    return ResponseEntity.ok("이미지가 성공적으로 삭제되었습니다.");
  }

//  @GetMapping(value = "/view")
//  public ImageResponse viewProfilePhoto() {
//    return fileService.viewFileList(requestDto);
//  }
}
