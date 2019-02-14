package com.sdata.ecommerce.service;

import com.sdata.ecommerce.api.category.SearchCategoryRequest;
import com.sdata.ecommerce.api.category.SearchCategoryResponse;
import com.sdata.ecommerce.domain.Category;

/**
 * @author nedli
 */
public interface CategoryService {
    Category getCategoryById(String id);

    SearchCategoryResponse search(SearchCategoryRequest request);

    int saveOrUpdateCategory(Category category);

    int deleteCategoryById(String id);
}
