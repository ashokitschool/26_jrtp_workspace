package in.ashokit.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
	@ExceptionHandler(value = OrderServiceException.class)
	public ResponseEntity<ExceptionResponse> handleOrderServcie(OrderServiceException ose) {
		ExceptionResponse errResp = new ExceptionResponse();
		errResp.setErrCode(ose.getErrCode());
		errResp.setErrMsg(ose.getMessage());
		return new ResponseEntity<>(errResp, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
