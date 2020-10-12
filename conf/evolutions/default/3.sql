# --- !Ups

INSERT INTO todo (body, note, category_id, status) 
  VALUES ("掃除", "お風呂の掃除をする", 1, 1);

INSERT INTO todo (body, note, category_id, status) 
  VALUES ("料理", "クリスマスのメニューを考える", 2, 0);

INSERT INTO todo (body, note, category_id, status)
  VALUES ("料理", "ケーキを作る", 3, 0);

INSERT INTO category (name) 
  VALUES ("優先度：高");

INSERT INTO category (name) 
  VALUES ("優先度：中");

INSERT INTO category (name) 
  VALUES ("優先度：低");

# --- !Downs
