package gamza.project.doaduo.error.requestError;

import gamza.project.doaduo.error.ErrorCode;

public class ForbiddenException extends BusinessException {

    public ForbiddenException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}