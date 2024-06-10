package in.ashokit.exception;

import org.springframework.http.HttpStatus;

public class CartNotFoundException extends RuntimeException {

    private final HttpStatus status;

    public CartNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
