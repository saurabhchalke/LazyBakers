package lazybakers.interceptor;

import lazybakers.framework.api.APIResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Exception Handler Controller Advice to catch all controller exceptions and respond gracefully to
 * the caller
 */
@ControllerAdvice
public class WebAppExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public APIResponse handleAnyException(Exception e) {
        return APIResponse.toErrorResponse(e.getMessage());
    }
}
