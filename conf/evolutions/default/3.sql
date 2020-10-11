# --- !Ups

INSERT INTO todo (body, note, category_id, status) 
  VALUES ("掃除", "お風呂の掃除をする", 1, 1);

INSERT INTO todo (body, note, category_id, status) 
  VALUES ("料理", "クリスマスのメニューを考える", 0, 0);

INSERT INTO category (name, color) 
  VALUES ("優先度：高", 0);

INSERT INTO category (name, color) 
  VALUES ("優先度：中", 1);

# --- !Downs
