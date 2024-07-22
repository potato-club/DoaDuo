package gamza.project.doaduo.error.requestError;

import gamza.project.doaduo.error.ErrorCode;

public class UnAuthorizedException  extends BusinessException{

    public UnAuthorizedException(String message, ErrorCode code) {
        super(message, code);
    }
}
