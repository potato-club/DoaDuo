package gamza.project.doaduo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchingRequestDTO {
    private double latitude;
    private double longitude;
    private String address;
    private String quickMessage;
    private String respondentName;
}