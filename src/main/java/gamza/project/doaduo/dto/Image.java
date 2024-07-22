package gamza.project.doaduo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Image {

  private final Long id;

  private final String originalFileName;

  private final String fileName;

  private final String fileUrl;

  private final String userEmail;


  public Image(Long id, String originalFileName, String fileName, String fileUrl, String email) {
    this.id = id;
    this.originalFileName = originalFileName;
    this.fileName = fileName;
    this.fileUrl = fileUrl;
    this.userEmail = email;
  }

}
