DROP TABLE IF EXISTS sdata_product;
CREATE TABLE sdata_product (
  id                VARCHAR(30) COMMENT '主键，UUID_SHORT()生成' PRIMARY KEY,
  name              VARCHAR(80) COMMENT '商品名字' NOT NULL,
  description       TEXT COMMENT '商品描述',
  keywords          VARCHAR(200) COMMENT '商品关键字',
  parent_category   VARCHAR(30) COMMENT '父Category',
  manufacturer      VARCHAR(200) COMMENT '制造商',
  msrp              DECIMAL(6, 2) COMMENT '建议零售价',
  sale_price        DECIMAL(6, 2) COMMENT '实际销售价格',
  active_begin_date VARCHAR(30) COMMENT '生效开始时间',
  active_end_date   VARCHAR(30) COMMENT '生效结束时间',
  template          VARCHAR(80) COMMENT '商品展示模板',
  create_by         VARCHAR(80) COMMENT '创建人',
  create_date       VARCHAR(80) COMMENT '创建时间',
  update_by         VARCHAR(80) COMMENT '更新人',
  update_date       VARCHAR(80) COMMENT '更新时间',
  active            BOOLEAN COMMENT '是否启用'
);

DROP TABLE IF EXISTS sdata_product_cross_sale;
CREATE TABLE sdata_product_cross_sale (
  id                    VARCHAR(30) COMMENT '主键，UUID_SHORT()生成' PRIMARY KEY,
  base_product_id       VARCHAR(30) COMMENT '主product id'     NOT NULL,
  cross_sale_product_id VARCHAR(30) COMMENT '交叉销售的proudct id' NOT NULL,
  display_order         INTEGER COMMENT '展示顺序',
  UNIQUE (base_product_id, cross_sale_product_id)
);

DROP TABLE IF EXISTS sdata_product_up_sale;
CREATE TABLE sdata_product_up_sale (
  id                 VARCHAR(30) COMMENT '主键，UUID_SHORT()生成' PRIMARY KEY,
  base_product_id    VARCHAR(30) COMMENT '主product id'     NOT NULL,
  up_sale_product_id VARCHAR(30) COMMENT '交叉销售的proudct id' NOT NULL,
  display_order      INTEGER COMMENT '展示顺序',
  UNIQUE (base_product_id, up_sale_product_id)
);

DROP TABLE IF EXISTS sdata_product_option;
CREATE TABLE sdata_product_option (
  id              VARCHAR(30) COMMENT '主键，UUID_SHORT()生成' PRIMARY KEY,
  base_product_id VARCHAR(30) COMMENT '主product id' NOT NULL,
  name            VARCHAR(30) COMMENT '选项名字',
  UNIQUE (base_product_id, name)
);

DROP TABLE IF EXISTS sdata_sku;
CREATE TABLE sdata_sku (
  id       VARCHAR(30) COMMENT '主键，UUID_SHORT()生成' PRIMARY KEY,
  name     VARCHAR(80) COMMENT 'SKU名字' NOT NULL,
  keywords VARCHAR(200) COMMENT '商品关键字'
);

DROP TABLE IF EXISTS sdata_sku_option_value;
CREATE TABLE sdata_sku_option_value (
  id                VARCHAR(30) COMMENT '主键，UUID_SHORT()生成' PRIMARY KEY,
  sku_id            VARCHAR(30) COMMENT 'sku id' NOT NULL,
  option_id         VARCHAR(30) COMMENT '选项ID'   NOT NULL,
  option_value      VARCHAR(60) COMMENT '选项值'    NOT NULL,
  active_begin_date VARCHAR(30) COMMENT '生效开始时间',
  active_end_date   VARCHAR(30) COMMENT '生效结束时间',
  active            BOOLEAN COMMENT '是否启用'
);

# Constraint Definitions
ALTER TABLE sdata_product
  ADD CONSTRAINT `fk_sdata_product_category_id` FOREIGN KEY (parent_category) REFERENCES sdata_category (id);
ALTER TABLE sdata_product_cross_sale
  ADD CONSTRAINT `fk_sdata_cross_product_base_product_id` FOREIGN KEY (base_product_id) REFERENCES sdata_product (id);
ALTER TABLE sdata_product_cross_sale
  ADD CONSTRAINT `fk_sdata_cross_product_cross_sale_product_id` FOREIGN KEY (cross_sale_product_id) REFERENCES sdata_product (id);
ALTER TABLE sdata_product_up_sale
  ADD CONSTRAINT `fk_sdata_product_up_sale_base_product_id` FOREIGN KEY (base_product_id) REFERENCES sdata_product (id);
ALTER TABLE sdata_product_up_sale
  ADD CONSTRAINT `fk_sdata_product_up_sale_product_id` FOREIGN KEY (up_sale_product_id) REFERENCES sdata_product (id);
ALTER TABLE sdata_product_option
  ADD CONSTRAINT `fk_sdata_product_option_base_product_id` FOREIGN KEY (base_product_id) REFERENCES sdata_product (id);

ALTER TABLE sdata_sku_option_value
  ADD CONSTRAINT `fk_sdata_sku_option_value_sku_id` FOREIGN KEY (sku_id) REFERENCES sdata_sku (id);
ALTER TABLE sdata_sku_option_value
  ADD CONSTRAINT `fk_sdata_sku_option_value_option_id` FOREIGN KEY (option_id) REFERENCES sdata_product_option (id);