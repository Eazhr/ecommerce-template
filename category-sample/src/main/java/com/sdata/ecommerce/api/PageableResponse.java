package com.sdata.ecommerce.api;


import com.sdata.ecommerce.exception.ErrorCode;

/**
 * @author nedli
 */
public class PageableResponse<T> extends BaseResponse<T> {
    public PageableResponse(long total) {
        this.total = total;
    }

    public PageableResponse(T data, long total) {
        super(data);
        this.total = total;
    }

    public PageableResponse(ErrorCode errorCode, long total) {
        super(errorCode);
        this.total = total;
    }

    private long total;

    /**
     * Gets total
     *
     * @return value of total
     */
    public long getTotal() {
        return total;
    }

    /**
     * Sets total
     */
    public void setTotal(long total) {
        this.total = total;
    }
}
