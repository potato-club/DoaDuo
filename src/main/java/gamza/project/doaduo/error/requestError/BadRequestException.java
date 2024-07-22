package gamza.project.doaduo.error.requestError;

import gamza.project.doaduo.error.ErrorCode;

public class BadRequestException extends BusinessException {

    public BadRequestException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}