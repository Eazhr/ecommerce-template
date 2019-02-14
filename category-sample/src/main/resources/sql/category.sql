SET SQL_MODE='ALLOW_INVALID_DATES';

DROP TABLE IF EXISTS sdata_category;
CREATE TABLE sdata_category (
  id              VARCHAR(30) PRIMARY KEY,
  name            VARCHAR(80)         NOT NULL,
  parent_category VARCHAR(30)         NOT NULL,
  url             VARCHAR(200) UNIQUE NOT NULL,
  template        VARCHAR(60),
  begin_date      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  end_date        TIMESTAMP
);

ALTER TABLE sdata_category
  ADD CONSTRAINT `fk_sdata_category_parent` FOREIGN KEY (parent_category) REFERENCES sdata_category (id);