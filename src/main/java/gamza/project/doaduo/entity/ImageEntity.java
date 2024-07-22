package gamza.project.doaduo.entity;

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
  private String email;


}
