package lazybakers.auth;

import javax.servlet.ServletException;

/**
 * Exception indicating Auth credentials missing.
 *
 */
public class AuthCredentialsMissingException extends ServletException {
    private static final long serialVersionUID = -8799659324455306881L;

    public AuthCredentialsMissingException(String message) {
        super(message);
    }
}
