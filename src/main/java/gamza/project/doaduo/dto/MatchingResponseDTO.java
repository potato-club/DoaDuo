package gamza.project.doaduo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class MatchingResponseDTO {
    private Long id;
    private String requesterName;
    private String currentAddress;
    private String quickMessage;
    private double latitude;
    private double longitude;
}