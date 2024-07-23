package gamza.project.doaduo.Service.inter;

import gamza.project.doaduo.dto.RequestUserLoginDto;
import gamza.project.doaduo.dto.RequestUserSignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

public interface UserService {

    void signUp(RequestUserSignUpDto dto, HttpServletResponse response, MultipartFile file) throws IOException;

    //void login(RequestUserLoginDto dto, HttpServletResponse response) throws IOException ;

    Map<String, String> login(RequestUserLoginDto dto, HttpServletResponse response);
    void reissueToken(HttpServletRequest request, HttpServletResponse response);
    String setBodyAtToken(String  email, HttpServletResponse response);
    String setBodyRtToken(String  email, HttpServletResponse response);
}
