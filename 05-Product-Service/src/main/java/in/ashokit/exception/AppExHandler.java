package in.ashokit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExHandler {

	@ExceptionHandler(value = ProductServiceException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceEx(ProductServiceException pse) {

		ErrorResponse resp = new ErrorResponse();
		resp.setErrorCode(pse.getErrorCode());
		resp.setMessage(pse.getMessage());

		return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
