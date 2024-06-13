package in.ashokit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExHandler {
	@ExceptionHandler(value = CartServiceException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceEx(CartServiceException ce) {

		ErrorResponse resp = new ErrorResponse();
		resp.setErrorCode(ce.getErrCode());
		resp.setMessage(ce.getMessage());

		return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
