package gamza.project.doaduo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class MatchingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requesterName;

    private String respondentName;

    @Column(nullable = false)
    private String currentAddress;

    @Column(nullable = false)
    private String quickMessage;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;


    private boolean requestState; // 요청자 상태

    private boolean responseState; // 응답자 상태

    private boolean acceptState; // 수락상태 기본 false

    private boolean rejectState; // 거절 상태 기본 false

    public void setAcceptState(boolean b) {
    }
}
