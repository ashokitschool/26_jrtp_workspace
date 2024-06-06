package in.ashokit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = AuthServiceException.class)
	public ResponseEntity<ExResponse> handleAuthServiceEx(AuthServiceException ae) {

		ExResponse exRes = new ExResponse();
		exRes.setErrMsg(ae.getMessage());
		exRes.setErrCode(ae.getErrCode());

		return new ResponseEntity<>(exRes, HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
