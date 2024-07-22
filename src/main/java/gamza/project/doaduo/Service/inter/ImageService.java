package gamza.project.doaduo.Service.inter;

import gamza.project.doaduo.dto.ImageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

  void uploadFile(MultipartFile file, String access) throws IOException;
  void updateFile(MultipartFile file,  String access) throws IOException;
  void deleteFile(String access) throws IOException;
  void viewFile(ImageRequest request) throws IOException;
}
