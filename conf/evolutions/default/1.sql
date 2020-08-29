# Todo スキーマ

# --- !Ups  必要なスキーマの変換方法の記述
CREATE TABLE todo (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  body varchar(255) NOT NULL,
  note text,
  category_id bigint(20) unsigned NOT NULL,
  status TINYINT unsigned NOT NULL,
  updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 
# --- !Downs  Upsの変換をもとに戻す方法の記述
DROP TABLE todo;
