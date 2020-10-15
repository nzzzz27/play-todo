# --- !Ups

INSERT INTO todo (body, note, category_id, status) 
  VALUES ("掃除", "お風呂の掃除をする", 1, 1);

INSERT INTO todo (body, note, category_id, status) 
  VALUES ("料理", "クリスマスのメニューを考える", 2, 3);

INSERT INTO todo (body, note, category_id, status)
VALUES ("料理", "クリスマスのメニューを考える", 2, 2);

INSERT INTO category (name, color)
  VALUES ("優先度：高", 1);

INSERT INTO category (name, color)
  VALUES ("優先度：中", 2);

# --- !Downs
