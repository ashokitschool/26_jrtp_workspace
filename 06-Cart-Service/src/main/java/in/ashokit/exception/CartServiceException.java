package in.ashokit.exception;

public class CartServiceException extends RuntimeException {

    private String errCode;

    public CartServiceException(String message, String errCode){
        super(message);
        this.errCode = errCode;
    }

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

 
}
