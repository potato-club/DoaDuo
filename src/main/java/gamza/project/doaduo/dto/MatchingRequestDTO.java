package gamza.project.doaduo.dto;

import gamza.project.doaduo.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchingRequestDTO {

    private String username;
    private double latitude;
    private double longitude;
    private String address;
    private String quickMessage;
}