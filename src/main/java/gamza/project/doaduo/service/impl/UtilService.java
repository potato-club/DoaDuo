package gamza.project.doaduo.Service.impl;

import gamza.project.doaduo.dto.MatchingRequestDTO;
import gamza.project.doaduo.entity.MatchingEntity;
import gamza.project.doaduo.Repository.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UtilService {

    private final MatchingRepository matchingRepository;

//    @Transactional
//    public Long createMatchingRequest(MatchingRequestDTO matchingRequestDTO) {
//        // MatchingEntity 생성
//        MatchingEntity matchingEntity = MatchingEntity.builder()
//                .latitude(matchingRequestDTO.getLatitude())
//                .longitude(matchingRequestDTO.getLongitude())
//                .currentAddress(matchingRequestDTO.getAddress())
//                .quickMessage(matchingRequestDTO.getQuickMessage())
//                .requesterName("Requester's Name") // 실제 요청자 이름을 설정해야 합니다.
//                .respondentName(matchingRequestDTO.getRespondentName())
//                .requestState(false)
//                .responseState(false)
//                .acceptState(false)
//                .rejectState(false)
//                .build();
//
//        // 데이터베이스에 저장
//        matchingEntity = matchingRepository.save(matchingEntity);
//
//        // 1분 후 만료 처리
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                expireRequest(matchingEntity.getId());
//            }
//        }, 60000); // 60,000 milliseconds = 1 minute
//
//        return matchingEntity.getId();
//    }
//
//    @Transactional
//    public void expireRequest(Long id) {
//        matchingRepository.findById(id).ifPresent(matchingEntity -> {
//            // 수락 상태를 만료로 변경 (예: acceptState = false)
//            matchingEntity.setAcceptState(false);
//            matchingRepository.save(matchingEntity);
//        });
//    }
}
