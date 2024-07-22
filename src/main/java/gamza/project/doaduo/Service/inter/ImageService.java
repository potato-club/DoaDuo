package gamza.project.doaduo.Service.inter;

import gamza.project.doaduo.dto.ImageRequest;
import gamza.project.doaduo.dto.ImageUpdate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

  void uploadFile(MultipartFile file, ImageRequest imageRequest) throws IOException;
  void updateFile(MultipartFile file,  ImageUpdate update) throws IOException;
  void deleteFile(ImageRequest request) throws IOException;
  void viewFile(ImageRequest request) throws IOException;
}
