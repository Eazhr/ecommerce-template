package com.sdata.ecommerce.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nedli
 */
@Getter
@Setter
@NoArgsConstructor
public class PageRequest extends BaseRequest {
    private Integer pageNum = 1;
    private Integer pageSize = Integer.MAX_VALUE;
}
