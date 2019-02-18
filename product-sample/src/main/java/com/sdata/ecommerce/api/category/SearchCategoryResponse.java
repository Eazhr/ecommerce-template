package com.sdata.ecommerce.api.category;

import com.sdata.ecommerce.api.PageableResponse;
import com.sdata.ecommerce.domain.Category;
import com.sdata.ecommerce.exception.ErrorCode;

import java.util.List;

/**
 * @author nedli
 */
public class SearchCategoryResponse extends PageableResponse<List<Category>> {
    public SearchCategoryResponse(long total) {
        super(total);
    }

    public SearchCategoryResponse(List<Category> data, long total) {
        super(data, total);
    }

    public SearchCategoryResponse(ErrorCode errorCode, long total) {
        super(errorCode, total);
    }
}
