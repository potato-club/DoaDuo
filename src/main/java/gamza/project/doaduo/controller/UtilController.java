package gamza.project.doaduo.controller;

import gamza.project.doaduo.dto.MatchingRequestDTO;
import gamza.project.doaduo.service.impl.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/util")
@CrossOrigin(originPatterns = "http://localhost:3000, localhost:3000")
public class UtilController {

    private final UtilService utilService;

    @PostMapping("/req")
    public ResponseEntity<Long> createMatchingRequest(@RequestBody MatchingRequestDTO matchingRequestDTO) {
        Long matchingId = utilService.createMatchingRequest(matchingRequestDTO);
        return ResponseEntity.ok(matchingId);
    }
}
