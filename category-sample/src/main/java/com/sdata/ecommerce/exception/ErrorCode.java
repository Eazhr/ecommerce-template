package com.sdata.ecommerce.exception;

/**
 * @author nedli
 */
public class ErrorCode {
    public static ErrorCode SUCCESS = new ErrorCode("E00000", "SUCCESS", 200);
    public static ErrorCode NOT_FOUND = new ErrorCode("E00001", "NOT_FOUND", 404);
    public static ErrorCode UNKNOWN_ERROR = new ErrorCode("E00002", "unknown error", 500);
    public static ErrorCode SERIALIZATION_ERROR = new ErrorCode("E00003", "serialization error", 500);
    public static ErrorCode HTTP_CODE_INVALID = new ErrorCode("E00004", "response http code invalid", 500);
    public static ErrorCode INVALID_REQUEST = new ErrorCode("E00005", "Bind error", 400);
    public static ErrorCode DATABASE_ERROR = new ErrorCode("E00006", "database error", 500);

    /**
     * code
     */
    private String code;

    /**
     * Error message.
     */
    private String errorMsg;

    /**
     * Http code.
     */
    private int httpCode;

    public ErrorCode(String code, String errorMsg, int httpCode) {
        this.code = code;
        this.errorMsg = errorMsg;
        this.httpCode = httpCode;
    }

    /**
     * Getter for code
     *
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for code
     *
     * @param code
     **/
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for errorMsg
     *
     * @return errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * Setter for errorMsg
     *
     * @param errorMsg
     **/
    public ErrorCode setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    /**
     * Getter for httpCode
     *
     * @return httpCode
     */
    public int getHttpCode() {
        return httpCode;
    }

    /**
     * Setter for httpCode
     *
     * @param httpCode
     **/
    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }
}
