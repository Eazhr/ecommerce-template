package com.sdata.ecommerce.service;

import com.github.pagehelper.PageHelper;
import com.sdata.ecommerce.api.category.SearchCategoryRequest;
import com.sdata.ecommerce.api.category.SearchCategoryResponse;
import com.sdata.ecommerce.domain.Category;
import com.sdata.ecommerce.domain.CategoryExample;
import com.sdata.ecommerce.mapper.CategoryMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.sdata.ecommerce.util.PageUtils.totalPages;

/**
 * @author nedli
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category getCategoryById(String id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public SearchCategoryResponse search(SearchCategoryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());

        CategoryExample example = new CategoryExample();
        if (StringUtils.isNotBlank(request.getKeywordLike())) {
            example.createCriteria().andNameLike(request.getKeywordLike());
        }
        List<Category> categories = categoryMapper.selectByExample(example);

        return new SearchCategoryResponse(categories, totalPages(categories));
    }

    @Override
    public int saveOrUpdateCategory(Category category) {
        if (StringUtils.isNotBlank(category.getId())) {
            return categoryMapper.updateByPrimaryKey(category);
        } else {
            return categoryMapper.insert(category);
        }
    }

    @Override
    public int deleteCategoryById(String id) {
        return categoryMapper.deleteByPrimaryKey(id);
    }
}
