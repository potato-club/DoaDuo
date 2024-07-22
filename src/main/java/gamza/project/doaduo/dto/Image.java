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

  private final Long userid;


  public Image(Long id, String originalFileName, String fileName, String fileUrl, Long userid) {
    this.id = id;
    this.originalFileName = originalFileName;
    this.fileName = fileName;
    this.fileUrl = fileUrl;
    this.userid = userid;
  }

}
