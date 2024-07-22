package gamza.project.doaduo.entity;

import gamza.project.doaduo.dto.Image;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String originalFileName;

  @Column(nullable = false)
  private String fileName;

  @Column(nullable = false)
  private String fileUrl;

  @Column
  private String userEmail;

  public static ImageEntity from(Image image) {
    ImageEntity imageEntity = new ImageEntity();
    imageEntity.id = image.getId();
    imageEntity.originalFileName = image.getOriginalFileName();
    imageEntity.fileName = image.getFileName();
    imageEntity.fileUrl = image.getFileUrl();
    imageEntity.userEmail = image.getUserEmail();

    return imageEntity;
  }

  public Image toEntity() {
    return Image.builder()
        .id(id)
        .originalFileName(originalFileName)
        .fileName(fileName)
        .fileUrl(fileUrl)
        .userEmail(userEmail)
        .build();
  }

}
