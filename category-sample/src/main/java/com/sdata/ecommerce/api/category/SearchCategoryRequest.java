package com.sdata.ecommerce.api.category;

import com.sdata.ecommerce.api.PageRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author nedli
 */
@Getter
@Setter
@NoArgsConstructor
public class SearchCategoryRequest extends PageRequest {
    private String keyword;

    public String getKeywordLike() {
        return StringUtils.isNotBlank(keyword) ? '%' + keyword.trim() + '%' : null;
    }
}
