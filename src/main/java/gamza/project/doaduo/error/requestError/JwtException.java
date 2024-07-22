package gamza.project.doaduo.error.requestError;

import gamza.project.doaduo.error.ErrorCode;

public class JwtException extends BusinessException {

    public JwtException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}