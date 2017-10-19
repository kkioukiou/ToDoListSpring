CREATE DATABASE IF NOT EXISTS todolist;
USE todolist;

CREATE TABLE IF NOT EXISTS to_do_list_item(
  id INT(11) NOT NULL AUTO_INCREMENT,
  item_value VARCHAR(255) NOT NULL,
  is_checked BOOLEAN DEFAULT FALSE,
  parent_id INT(11) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_todolist_idx FOREIGN KEY (parent_id) REFERENCES to_do_list_item (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
