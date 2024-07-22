package gamza.project.doaduo.error.requestError;

import gamza.project.doaduo.error.ErrorCode;

public class NotFoundException extends BusinessException {

    public NotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}