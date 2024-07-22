package gamza.project.doaduo.error.requestError;

import gamza.project.doaduo.error.ErrorCode;

public class ExpiredRefreshTokenException extends BusinessException {

    public ExpiredRefreshTokenException(String message, ErrorCode code) {
        super(message, code);
    }
}