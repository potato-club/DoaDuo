package gamza.project.doaduo.controller;

import gamza.project.doaduo.dto.AcceptStateDTO;
import gamza.project.doaduo.dto.MatchingRequestDTO;
import gamza.project.doaduo.entity.MatchingEntity;
import gamza.project.doaduo.service.impl.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/util")
@CrossOrigin(originPatterns = "http://localhost:3000, localhost:3000")
public class UtilController {

    private final UtilService utilService;

    @PostMapping("/request")
    public ResponseEntity<Long> createMatchingRequest(@RequestBody MatchingRequestDTO matchingRequestDTO) {
        Long id = utilService.createMatchingRequest(matchingRequestDTO);
        return ResponseEntity.ok(id);
    }

    @PostMapping("/accept/{id}")
    public ResponseEntity<Void> acceptMatchingRequest(@PathVariable Long id) {
        utilService.acceptMatchingRequest(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<AcceptStateDTO> getMatchingRequestAcceptState(@PathVariable Long id) {
        AcceptStateDTO acceptStateDTO = utilService.getMatchingRequestAcceptState(id);
        return ResponseEntity.ok(acceptStateDTO);
    }
}
