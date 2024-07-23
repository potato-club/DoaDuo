package gamza.project.doaduo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import gamza.project.doaduo.entity.UserRole;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Builder

public class HistoryRequestDTO {

  private String cause;

  private String content;

  private String writer;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime date;
}
