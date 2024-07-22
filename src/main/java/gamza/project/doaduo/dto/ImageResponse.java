package gamza.project.doaduo.dto;

import lombok.Builder;

public class ImageResponse {

  private final String fileName;

  private final String fileUrl;

  @Builder
  public ImageResponse(String fileName, String fileUrl) {
    this.fileName = fileName;
    this.fileUrl = fileUrl;
  }
}
