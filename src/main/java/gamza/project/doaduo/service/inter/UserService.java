package gamza.project.doaduo.Service.inter;

import gamza.project.doaduo.dto.RequestUserLoginDto;
import gamza.project.doaduo.dto.RequestUserSignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {

    void signUp(RequestUserSignUpDto dto, HttpServletResponse response, MultipartFile file) throws IOException;

    void login(RequestUserLoginDto dto, HttpServletResponse response) throws IOException ;

    void reissueToken(HttpServletRequest request, HttpServletResponse response);

    void setTokenInHeader(String email, HttpServletResponse response);
    void setBodyInHeader(String  email, HttpServletResponse response) throws IOException;
}
