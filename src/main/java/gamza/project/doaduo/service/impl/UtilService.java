package gamza.project.doaduo.service.impl;

import gamza.project.doaduo.dto.MatchingRequestDTO;
import gamza.project.doaduo.entity.MatchingEntity;
import gamza.project.doaduo.repository.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Timer;
import java.util.TimerTask;

@Service
@RequiredArgsConstructor
public class UtilService {

    private final MatchingRepository matchingRepository;

    @Transactional
    public Long createMatchingRequest(MatchingRequestDTO matchingRequestDTO) {
        // MatchingEntity 생성
        MatchingEntity matchingEntity = MatchingEntity.builder()
                .latitude(matchingRequestDTO.getLatitude())
                .longitude(matchingRequestDTO.getLongitude())
                .currentAddress(matchingRequestDTO.getAddress())
                .quickMessage(matchingRequestDTO.getQuickMessage())
                .build();

        // 데이터베이스에 저장
        matchingEntity = matchingRepository.save(matchingEntity);

        // 1분 후 만료 처리
        MatchingEntity finalMatchingEntity = matchingEntity;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                expireRequest(finalMatchingEntity.getId());
            }
        }, 60000); // 60,000 milliseconds = 1 minute

        return matchingEntity.getId();
    }

    @Transactional
    public void expireRequest(Long id) {
        matchingRepository.findById(id).ifPresent(matchingEntity -> {
            // 수락 상태를 만료로 변경 (예: acceptState = false)
            matchingEntity.setAcceptState(false);
            matchingRepository.save(matchingEntity);
        });
    }
}
