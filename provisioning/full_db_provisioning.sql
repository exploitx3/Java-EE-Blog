
CREATE TABLE USERS
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(30),
  password VARCHAR(200),
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  email VARCHAR(50),
  type VARCHAR(50) DEFAULT 'NormalUser'
);

CREATE TABLE POSTS
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  author_id INT(11) NOT NULL,
  title VARCHAR(50),
  content TEXT,
  date DATE,
  visits INT(11) DEFAULT '0',
  CONSTRAINT id FOREIGN KEY (author_id) REFERENCES USERS (id)
);

CREATE TABLE COMMENTS
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  author_id INT(11) NOT NULL,
  post_id INT(11) NOT NULL,
  title VARCHAR(50),
  content TEXT,
  date DATE,
  CONSTRAINT author_id FOREIGN KEY (author_id) REFERENCES USERS (id),
  CONSTRAINT post_id FOREIGN KEY (post_id) REFERENCES POSTS (id)
);
CREATE INDEX author_id ON COMMENTS (author_id);
CREATE INDEX post_id ON COMMENTS (post_id);
CREATE TABLE TAGS
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  name VARCHAR(15)
);
CREATE TABLE POST_TAG
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  post_id INT(11) NOT NULL,
  tag_id INT(11) NOT NULL
);


INSERT INTO blog.USERS (username, password, first_name, last_name, email, type) VALUES ('Admin', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Admin', 'Admin', 'georgi@abv.bg', 'Administrator');
INSERT INTO blog.USERS (username, password, first_name, last_name, email, type) VALUES ('Gosho', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Goergi', 'SomeOne', 'Georgi@abv.bg', 'NormalUser');
INSERT INTO blog.USERS (username, password, first_name, last_name, email, type) VALUES ('Pesho', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'Petar', 'Petrov', 'pesho@abv.bg', 'NormalUser');


INSERT INTO blog.COMMENTS (author_id, post_id, title, content, date) VALUES (7, 70, 'Agree', 'Yes I agree.<br>', '2016-11-21');
INSERT INTO blog.COMMENTS (author_id, post_id, title, content, date) VALUES (7, 70, 'Although', 'Although functions are useful too', '2016-11-21');
INSERT INTO blog.COMMENTS (author_id, post_id, title, content, date) VALUES (6, 69, 'New Comment', 'Comment', '2016-11-21');
INSERT INTO blog.COMMENTS (author_id, post_id, title, content, date) VALUES (6, 70, 'LastComment', 'This is the last comment', '2016-11-21');

INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (67, 70);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (67, 71);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (68, 72);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (68, 73);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (69, 74);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (69, 75);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (70, 76);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (70, 77);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (70, 78);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (71, 79);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (72, 80);
INSERT INTO blog.POST_TAG (post_id, tag_id) VALUES (72, 81);

INSERT INTO blog.POSTS (author_id, title, content, date, visits) VALUES (6, 'Why do we use it?', '
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mattis
velit vel tellus mollis porttitor. Fusce semper et nibh vitae dignissim.
 Maecenas semper dignissim tellus, eget dictum erat eleifend in. Etiam
mauris mauris, ornare quis ultrices nec, lobortis sed urna. Aliquam
sapien libero, euismod nec posuere vel, tempor tincidunt libero. Cum
sociis natoque penatibus et magnis dis parturient montes, nascetur
ridiculus mus. Suspendisse pharetra fringilla arcu, quis mollis est
eleifend vel. Quisque et lacus facilisis, laoreet felis vel, auctor mi.
Morbi eros dolor, elementum vel convallis ut, aliquet in turpis. Fusce
egestas ornare suscipit. In blandit dolor ac ante aliquam, eget faucibus
 augue semper. Donec varius sodales consectetur. Sed ornare quam in
neque venenatis sollicitudin sed ut enim.
', '2016-11-21', 0);
INSERT INTO blog.POSTS (author_id, title, content, date, visits) VALUES (6, 'New Post', '
Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean mattis
velit vel tellus mollis porttitor. Fusce semper et nibh vitae dignissim.
 Maecenas semper dignissim tellus, eget dictum erat eleifend in. Etiam
mauris mauris, ornare quis ultrices nec, lobortis sed urna. Aliquam
sapien libero, euismod nec posuere vel, tempor tincidunt libero. Cum
sociis natoque penatibus et magnis dis parturient montes, nascetur
ridiculus mus. Suspendisse pharetra fringilla arcu, quis mollis est
eleifend vel. Quisque et lacus facilisis, laoreet felis vel, auctor mi.
Morbi eros dolor, elementum vel convallis ut, aliquet in turpis. Fusce
egestas ornare suscipit. In blandit dolor ac ante aliquam, eget faucibus
 augue semper. Donec varius sodales consectetur. Sed ornare quam in
neque venenatis sollicitudin sed ut enim.
', '2016-11-21', 0);
INSERT INTO blog.POSTS (author_id, title, content, date, visits) VALUES (6, 'Maecenas feugiat arcu pellentesque', 'Maecenas feugiat arcu pellentesque, auctor felis sed, lacinia urna.
Maecenas suscipit aliquam est, nec semper tortor. Maecenas rhoncus
sapien ut tellus lobortis, in sagittis leo vulputate. In fermentum
efficitur ligula, nec vehicula elit tincidunt non. Maecenas dignissim
erat eu arcu luctus luctus. In sagittis commodo magna ut aliquam. Sed
bibendum nisl a lacinia volutpat. Phasellus facilisis interdum maximus.
Etiam suscipit lacus ante, at iaculis lacus elementum quis. Suspendisse
ultricies condimentum pulvinar. Aenean lacus sem, lacinia a hendrerit
nec, interdum eget erat. Nulla dictum, ligula eu tristique aliquam,
tellus leo condimentum nisl, sit amet molestie libero magna et nunc.
Morbi tristique, quam non imperdiet pretium, odio sem ornare enim, non
scelerisque diam ante finibus mauris. Pellentesque lacinia cursus lectus
 vehicula facilisis.
', '2016-11-21', 8);
INSERT INTO blog.POSTS (author_id, title, content, date, visits) VALUES (6, 'Classes are cool', 'Class aptent taciti sociosqu ad litora torquent per conubia nostra, per
inceptos himenaeos. Quisque accumsan nulla ut orci imperdiet eleifend.
Phasellus maximus nec neque id faucibus. Vestibulum in ex at turpis
ultrices varius in a quam. Nunc ornare ac urna quis faucibus. Aliquam
mollis eget sem rhoncus mollis. Integer tempus tellus urna, non pharetra
 orci mollis in. Pellentesque et neque nec diam sagittis elementum ac id
 nisi. Vivamus at iaculis dolor. Etiam vitae nibh nisl. Praesent cursus
lacus a lorem congue, at elementum purus dictum. Maecenas sed nisi vel
urna hendrerit pulvinar. Etiam dapibus erat ut bibendum dapibus. Etiam
porttitor sem nec neque lobortis vehicula. Curabitur vitae tincidunt
nunc, a ullamcorper dolor. Phasellus nibh mi, ultrices vel aliquam quis,
 posuere nec nisl.
', '2016-11-21', 23);
INSERT INTO blog.POSTS (author_id, title, content, date, visits) VALUES (7, 'Morbi sit amet nulla felis', 'Morbi sit amet nulla felis. Praesent bibendum vulputate mi, in sodales
sem varius molestie. Phasellus non nulla tincidunt, gravida leo quis,
hendrerit ligula. Donec ullamcorper porta neque vitae tincidunt. Ut
tincidunt sagittis sem, sit amet lacinia massa dignissim et. Fusce sit
amet sem eget tortor sodales suscipit. VMorbi sit amet nulla felis. Praesent bibendum vulputate mi, in sodales
sem varius molestie. Phasellus non nulla tincidunt, gravida leo quis,
hendrerit ligula. Donec ullamcorper porta neque vitae tincidunt. Ut
tincidunt sagittis sem, sit amet lacinia massa dignissim et. Fusce sit
amet sem eget tortor sodales suscipit. V', '2016-11-21', 0);
INSERT INTO blog.POSTS (author_id, title, content, date, visits) VALUES (7, 'Pesho plet plete', 'Morbi sit amet nulla felis pesho Morbi sit amet nulla felis peshoMorbi sit amet nulla felis pesho Morbi sit amet nulla felis peshoMorbi sit amet nulla felis pesho Morbi sit amet nulla felis peshoMorbi sit amet nulla felis pesho Morbi sit amet nulla felis peshoMorbi sit amet nulla felis pesho Morbi sit amet nulla felis peshoMorbi sit amet nulla felis pesho Morbi sit amet nulla felis peshoMorbi sit amet nulla felis pesho Morbi sit amet nulla felis peshoMorbi sit amet nulla felis pesho Morbi sit amet nulla felis pesho', '2016-11-21', 14);

INSERT INTO blog.TAGS (name) VALUES ('why');
INSERT INTO blog.TAGS (name) VALUES ('lorem');
INSERT INTO blog.TAGS (name) VALUES ('post');
INSERT INTO blog.TAGS (name) VALUES ('test');
INSERT INTO blog.TAGS (name) VALUES ('auctor');
INSERT INTO blog.TAGS (name) VALUES ('Maecenas');
INSERT INTO blog.TAGS (name) VALUES ('test');
INSERT INTO blog.TAGS (name) VALUES ('class');
INSERT INTO blog.TAGS (name) VALUES ('cool');
INSERT INTO blog.TAGS (name) VALUES ('test');
INSERT INTO blog.TAGS (name) VALUES ('pesho');
INSERT INTO blog.TAGS (name) VALUES ('test');