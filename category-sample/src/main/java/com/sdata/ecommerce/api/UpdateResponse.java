package com.sdata.ecommerce.api;

import com.sdata.ecommerce.exception.ErrorCode;

/**
 * @author nedli
 */
public class UpdateResponse extends BaseResponse<Integer> {
    public UpdateResponse() {
    }

    public UpdateResponse(Integer data) {
        super(data);
    }

    public UpdateResponse(ErrorCode errorCode) {
        super(errorCode);
    }
}
