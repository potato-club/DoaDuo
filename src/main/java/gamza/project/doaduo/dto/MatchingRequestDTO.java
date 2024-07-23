package gamza.project.doaduo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchingRequestDTO {
    private double latitude;        // 위도
    private double longitude;       // 경도
    private String address;         // 주소
    private String quickMessage;    // 요구사항
    private String respondentName;  // 응답자 이름
}