package in.ashokit.exception;

public class OrderServiceException extends RuntimeException {
	private String errCode;

	public OrderServiceException() {

	}

	public OrderServiceException(String msg, String errCode) {
		super(msg);
		this.errCode = errCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

}
