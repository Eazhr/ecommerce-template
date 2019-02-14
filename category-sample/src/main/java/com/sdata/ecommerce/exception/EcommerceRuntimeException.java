package com.sdata.ecommerce.exception;

/**
 * @author nedli
 */
public class EcommerceRuntimeException extends RuntimeException {
    /**
     * Error code to describe this exception.
     */
    private ErrorCode errorCode;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public EcommerceRuntimeException() {
        super();
    }

    /**
     * override super class to add error code.
     *
     * @param errorCode custom error code.
     */
    public EcommerceRuntimeException(ErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
    }

    /**
     * override super class to add error code.
     *
     * @param message   the detail message. The detail message is saved for
     *                  later retrieval by the {@link #getMessage()} method.
     * @param errorCode custom error code.
     */
    public EcommerceRuntimeException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * override super class to add error code.
     *
     * @param message   the detail message (which is saved for later retrieval
     *                  by the {@link #getMessage()} method).
     * @param cause     the cause (which is saved for later retrieval by the
     *                  {@link #getCause()} method).  (A <tt>null</tt> value is
     *                  permitted, and indicates that the cause is nonexistent or
     *                  unknown.)
     * @param errorCode custom error code.
     */
    public EcommerceRuntimeException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * override super class to add error code.
     *
     * @param cause     the cause (which is saved for later retrieval by the
     *                  {@link #getCause()} method).  (A <tt>null</tt> value is
     *                  permitted, and indicates that the cause is nonexistent or
     *                  unknown.)
     * @param errorCode custom error code.
     * @since 1.4
     */
    public EcommerceRuntimeException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    /**
     * override super class to add error code.
     *
     * @param message            the detail message.
     * @param cause              the cause.  (A {@code null} value is permitted,
     *                           and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression  whether or not suppression is enabled
     *                           or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     * @param errorCode          custom error code.
     * @since 1.7
     */
    public EcommerceRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ErrorCode errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    /**
     * @return Gets the value of errorCode and returns errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the errorCode
     * You can use getErrorCode() to get the value of errorCode
     */
    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
