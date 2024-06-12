package in.ashokit.exception;


public class ReportsServiceException extends RuntimeException{
	
	private String errorCode;

	public ReportsServiceException(String msg, String errorCode) {
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
