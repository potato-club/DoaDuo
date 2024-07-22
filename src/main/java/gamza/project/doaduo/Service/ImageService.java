package gamza.project.doaduo.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

  void uploadFile(MultipartFile file, String userEmail);
  void deleteFile(Long imageId);
}
