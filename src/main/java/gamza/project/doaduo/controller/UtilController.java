package gamza.project.doaduo.Controller;

import gamza.project.doaduo.dto.AcceptStateDTO;
import gamza.project.doaduo.dto.MatchingRequestDTO;
import gamza.project.doaduo.Service.impl.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/status/{id}") //상태 조회
  public ResponseEntity<AcceptStateDTO> getMatchingRequestAcceptState(@PathVariable Long id) {
    AcceptStateDTO acceptStateDTO = utilService.getMatchingRequestAcceptState(id);
    return ResponseEntity.ok(acceptStateDTO);
  }



}