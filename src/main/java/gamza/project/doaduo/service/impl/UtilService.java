package gamza.project.doaduo.service.impl;

import ch.qos.logback.core.subst.NodeToStringTransformer;
import gamza.project.doaduo.dto.AcceptStateDTO;
import gamza.project.doaduo.dto.MatchingRequestDTO;
import gamza.project.doaduo.entity.MatchingEntity;
import gamza.project.doaduo.error.ErrorCode;
import gamza.project.doaduo.error.requestError.NotFoundException;
import gamza.project.doaduo.repository.MatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UtilService {

    private final MatchingRepository matchingRepository;

    @Transactional
    public Long createMatchingRequest(MatchingRequestDTO matchingRequestDTO) {
        MatchingEntity matchingEntity = MatchingEntity.builder()
                .latitude(matchingRequestDTO.getLatitude())
                .longitude(matchingRequestDTO.getLongitude())
                .currentAddress(matchingRequestDTO.getAddress())
                .quickMessage(matchingRequestDTO.getQuickMessage())
                .respondentName(matchingRequestDTO.getRespondentName())
                .acceptState(false)
                .build();

        matchingEntity = matchingRepository.save(matchingEntity);
        return matchingEntity.getId();
    }

    @Transactional
    public void acceptMatchingRequest(Long id) {
        MatchingEntity matchingEntity = matchingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Matching request not found", ErrorCode.NOT_FOUND_EXCEPTION));
        matchingEntity.setAcceptState(true);
        matchingRepository.save(matchingEntity);
    }

    @Transactional(readOnly = true)
    public AcceptStateDTO getMatchingRequestAcceptState(Long id) {
        MatchingEntity matchingEntity = matchingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Matching request not found", ErrorCode.NOT_FOUND_EXCEPTION));
        return new AcceptStateDTO(matchingEntity.isAcceptState());
    }


}
