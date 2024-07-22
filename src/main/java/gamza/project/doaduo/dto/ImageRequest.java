package gamza.project.doaduo.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ImageRequest {

  private final Long user_Id;

  private final String user_email;

  @Builder
  public ImageRequest(Long userId, String userEmail) {
    user_Id = userId;
    user_email = userEmail;
  }
}
