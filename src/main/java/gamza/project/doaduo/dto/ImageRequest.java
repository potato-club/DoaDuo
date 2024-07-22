package gamza.project.doaduo.dto;

import gamza.project.doaduo.entity.UserRole;
import lombok.Builder;
import lombok.Getter;

import javax.swing.text.html.parser.Entity;

@Getter
public class ImageRequest {

  private final UserRole userRole;

  private final String user_email;

  @Builder
  public ImageRequest(UserRole userRole, String userEmail) {
    this.userRole = userRole;
    this.user_email = userEmail;
  }
}
