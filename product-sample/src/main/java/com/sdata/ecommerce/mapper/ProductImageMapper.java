package com.sdata.ecommerce.mapper;

import com.sdata.ecommerce.domain.ProductImage;
import com.sdata.ecommerce.domain.ProductImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductImageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    long countByExample(ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    int deleteByExample(ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    int insert(ProductImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    int insertSelective(ProductImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    List<ProductImage> selectByExample(ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    ProductImage selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    int updateByExampleSelective(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    int updateByExample(@Param("record") ProductImage record, @Param("example") ProductImageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    int updateByPrimaryKeySelective(ProductImage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product_image
     *
     * @mbg.generated Tue Feb 19 08:49:00 CST 2019
     */
    int updateByPrimaryKey(ProductImage record);
}