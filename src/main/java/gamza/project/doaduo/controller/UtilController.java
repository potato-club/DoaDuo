package gamza.project.doaduo.controller;

import gamza.project.doaduo.dto.AcceptStateDTO;
import gamza.project.doaduo.dto.MatchingRequestDTO;
import gamza.project.doaduo.dto.MatchingResponseDTO;
import gamza.project.doaduo.entity.MatchingEntity;
import gamza.project.doaduo.service.impl.UtilService;
import gamza.project.doaduo.service.inter.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/util")
@CrossOrigin(originPatterns = "http://localhost:3000, localhost:3000")
public class UtilController {

    private final UtilService utilService;

    @PostMapping("/request")
    public ResponseEntity<Long> createMatchingRequest(@RequestBody MatchingRequestDTO matchingRequestDTO, HttpServletRequest request) {
        Long id = utilService.createMatchingRequest(matchingRequestDTO, request);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<String> acceptMatchingRequest(@PathVariable("id") Long id, HttpServletRequest request) {
        String username = utilService.acceptMatchingRequest(id,request);
        return ResponseEntity.ok().body(username);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<AcceptStateDTO> getMatchingRequestAcceptState(@PathVariable Long id) {
        AcceptStateDTO acceptStateDTO = utilService.getMatchingRequestAcceptState(id);
        return ResponseEntity.ok(acceptStateDTO);
    }

    @GetMapping("/names")
    public ResponseEntity<Map<Long, String>> getUserNamesByIds(@RequestParam List<Long> ids) {
        Map<Long, String> userNames = utilService.getUserNamesByIds(ids);
        return ResponseEntity.ok(userNames);
    }

    @GetMapping("/unaccepted")
    public ResponseEntity<List<MatchingResponseDTO>> getUnacceptedMatchingRequests() {
        List<MatchingResponseDTO> response = utilService.getUnacceptedMatchingRequests();
        return ResponseEntity.ok(response);
    }
}
