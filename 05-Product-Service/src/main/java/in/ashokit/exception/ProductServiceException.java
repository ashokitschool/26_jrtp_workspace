package in.ashokit.exception;

public class ProductServiceException extends RuntimeException {

	private String errorCode;

	public ProductServiceException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
