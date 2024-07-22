package gamza.project.doaduo.service.inter;

import gamza.project.doaduo.dto.RequestUserLoginDto;
import gamza.project.doaduo.dto.RequestUserSignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

public interface UserService {

    void signUp(RequestUserSignUpDto dto, HttpServletResponse response);

    Map<String, String> login(RequestUserLoginDto dto, HttpServletResponse response);

//    void setBodyToken(String email, HttpServletResponse response);

    void reissueToken(HttpServletRequest request, HttpServletResponse response);

    String setBodyAtToken(String  email, HttpServletResponse response);
    String setBodyRtToken(String  email, HttpServletResponse response);

//    void setTokenInHeader(String email, HttpServletResponse response);
}
