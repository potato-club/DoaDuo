package gamza.project.doaduo.service.impl;

import ch.qos.logback.core.subst.NodeToStringTransformer;
import gamza.project.doaduo.dto.AcceptStateDTO;
import gamza.project.doaduo.dto.MatchingRequestDTO;
import gamza.project.doaduo.dto.MatchingResponseDTO;
import gamza.project.doaduo.entity.MatchingEntity;
import gamza.project.doaduo.entity.UserEntity;
import gamza.project.doaduo.error.ErrorCode;
import gamza.project.doaduo.error.requestError.BadRequestException;
import gamza.project.doaduo.error.requestError.NotFoundException;
import gamza.project.doaduo.jwt.JwtTokenProvider;
import gamza.project.doaduo.repository.MatchingRepository;
import gamza.project.doaduo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UtilService {

    private final MatchingRepository matchingRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public Long createMatchingRequest(MatchingRequestDTO matchingRequestDTO,HttpServletRequest request) {

        String token = jwtTokenProvider.resolveAccessToken(request);
        int role = Integer.parseInt(jwtTokenProvider.extractRole(token)); // 요청자 0
        if(role == 1) {
            throw new BadRequestException("요청자만 요청 가능", ErrorCode.ACCESS_DENIED_EXCEPTION);
        }

        Long id = jwtTokenProvider.extractId(token);
        Optional<UserEntity> user = userRepository.findById(id);

        MatchingEntity matchingEntity = MatchingEntity.builder()
                .requesterName(user.get().getUsername())
                .latitude(matchingRequestDTO.getLatitude())
                .longitude(matchingRequestDTO.getLongitude())
                .currentAddress(matchingRequestDTO.getAddress())
                .quickMessage(matchingRequestDTO.getQuickMessage())
                .acceptState(false)
                .build();

        matchingEntity = matchingRepository.save(matchingEntity);
        return matchingEntity.getId();
    }

    @Transactional
    public String acceptMatchingRequest(Long id, HttpServletRequest request) {
        String token = jwtTokenProvider.resolveAccessToken(request);
        int role = Integer.parseInt(jwtTokenProvider.extractRole(token)); // 응답자 1
        if(role == 0) {
            throw new BadRequestException("응답자만 응답 가능", ErrorCode.ACCESS_DENIED_EXCEPTION);
        }

        MatchingEntity matchingEntity = matchingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Matching request not found", ErrorCode.NOT_FOUND_EXCEPTION));
        matchingEntity.setAcceptState(true);
        matchingRepository.save(matchingEntity);

        Long userId = jwtTokenProvider.extractId(token);

        Optional<UserEntity> user = userRepository.findById(userId);


        return user.get().getUsername();
    }

    @Transactional(readOnly = true)
    public AcceptStateDTO getMatchingRequestAcceptState(Long id) {
        MatchingEntity matchingEntity = matchingRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Matching request not found", ErrorCode.NOT_FOUND_EXCEPTION));
        return new AcceptStateDTO(matchingEntity.isAcceptState());
    }

    public Map<Long, String> getUserNamesByIds(List<Long> ids) {
        return userRepository.findAllById(ids).stream()
                .collect(Collectors.toMap(UserEntity::getId, UserEntity::getUsername));
    }

    public List<MatchingResponseDTO> getUnacceptedMatchingRequests() {
        List<MatchingEntity> matchingEntities = matchingRepository.findByAcceptStateFalse();

        return matchingEntities.stream()
                .map(entity -> new MatchingResponseDTO(
                        entity.getId(),
                        entity.getRequesterName(),
                        entity.getCurrentAddress(),
                        entity.getQuickMessage(),
                        entity.getLatitude(),
                        entity.getLongitude()
                ))
                .collect(Collectors.toList());
    }

}
