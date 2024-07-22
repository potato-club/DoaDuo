package gamza.project.doaduo.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageUpdate {

  private final String originalFileName;

  private final String fileName;

  private final String fileUrl;

  private final String userEmail;

  @Builder
  public ImageUpdate(String key, String fileName, String fileUrl, String userEmail) {
    this.originalFileName = key;
    this.fileName = fileName;
    this.fileUrl = fileUrl;
    this.userEmail = userEmail;
  }
}
