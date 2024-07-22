package gamza.project.doaduo.controller;

import gamza.project.doaduo.dto.RequestUserLoginDto;
import gamza.project.doaduo.dto.RequestUserSignUpDto;
import gamza.project.doaduo.service.inter.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(originPatterns = "http://localhost:3000, localhost:3000")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody RequestUserSignUpDto dto, HttpServletResponse response) {
        userService.signUp(dto, response);
        return ResponseEntity.ok().body("Success Sing Up!\nIf you want to see the PK value, please ask SH :)");
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody RequestUserLoginDto dto, HttpServletResponse response) {
//        userService.login(dto, response);
//        return ResponseEntity.ok().body("Success Login!");
//    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody RequestUserLoginDto dto, HttpServletResponse response) {
        Map<String, String> tokens = userService.login(dto, response);
        return ResponseEntity.ok(tokens);
    }

    @GetMapping("/reissue")
    public ResponseEntity<String> reissue(HttpServletRequest request, HttpServletResponse response) {
        userService.reissueToken(request, response);
        return ResponseEntity.ok().body("Success reissue Token!");
    }



}