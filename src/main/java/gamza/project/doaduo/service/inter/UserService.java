package gamza.project.doaduo.service.inter;

import gamza.project.doaduo.dto.RequestUserLoginDto;
import gamza.project.doaduo.dto.RequestUserSignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

    void signUp(RequestUserSignUpDto dto, HttpServletResponse response);

    void login(RequestUserLoginDto dto, HttpServletResponse response);

    void reissueToken(HttpServletRequest request, HttpServletResponse response);

    void setTokenInHeader(String email, HttpServletResponse response);
}
