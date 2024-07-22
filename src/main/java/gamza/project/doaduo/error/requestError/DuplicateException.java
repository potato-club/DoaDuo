package gamza.project.doaduo.error.requestError;

import gamza.project.doaduo.error.ErrorCode;

public class DuplicateException extends BusinessException {

    public DuplicateException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

}