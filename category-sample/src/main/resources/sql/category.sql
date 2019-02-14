DROP TABLE IF EXISTS sdata_category;
CREATE TABLE sdata_category (
  id              VARCHAR(30) PRIMARY KEY,
  name            VARCHAR(80)         NOT NULL,
  parent_category VARCHAR(30),
  url             VARCHAR(200) UNIQUE NOT NULL,
  template        VARCHAR(60),
  begin_date      VARCHAR(20),
  end_date        VARCHAR(20),
  active          BOOLEAN   DEFAULT TRUE
);

ALTER TABLE sdata_category
  ADD CONSTRAINT `fk_sdata_category_parent` FOREIGN KEY (parent_category) REFERENCES sdata_category (id);

INSERT INTO sdata_category(id, name, parent_category, url, template, begin_date, end_date, active)
VALUE ('1', 'Apple', NULL, '/apple', NULL, NULL, NULL, True);