package gamza.project.doaduo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/util")
@CrossOrigin(originPatterns = "http://localhost:3000, localhost:3000")
public class UtilController {
}
