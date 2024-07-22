package gamza.project.doaduo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class MatchingEntity {

    public static final int STATUS_PENDING = 0;
    public static final int STATUS_ACCEPTED = 1;
    public static final int STATUS_EXPIRED = 2;

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

    private LocalDateTime createdAt;

    private boolean requestState; // 요청자 상태

    private boolean responseState; // 응답자 상태

    private boolean acceptState; // 수락상태 기본 false

    private boolean rejectState; // 거절 상태 기본 false

}
