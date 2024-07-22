package gamza.project.doaduo.error.requestError;

import gamza.project.doaduo.error.ErrorCode;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}