INSERT INTO user (ID, USERNAME, PASSWORD, avatar, LASTNAME, EMAIL, ACTIVATED) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c', 'admin', 'admin@admin.com', 1);
INSERT INTO user (ID, USERNAME, PASSWORD, avatar, LASTNAME, EMAIL, ACTIVATED) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c', 'user', 'enabled@user.com', 1);
INSERT INTO user (ID, USERNAME, PASSWORD, avatar, LASTNAME, EMAIL, ACTIVATED) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c', 'user', 'disabled@user.com', 0);

INSERT INTO authority (NAME) VALUES ('ROLE_USER');
INSERT INTO authority (NAME) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_USER');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (2, 'ROLE_USER');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (3, 'ROLE_USER');

INSERT INTO `aplmu`.`article`(`id`,`authorid`,`content`,`created_time_stamp`,`page_view`,`title`,`title_image`)VALUES(1,1,"# Hello \n\n ## World",1594174524971,0,"你好1","https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c");
INSERT INTO `aplmu`.`article`(`id`,`authorid`,`content`,`created_time_stamp`,`page_view`,`title`,`title_image`)VALUES(2,2,"# Hello \n\n ## World",1594174524971,0,"你好2","https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c");
INSERT INTO `aplmu`.`article`(`id`,`authorid`,`content`,`created_time_stamp`,`page_view`,`title`,`title_image`)VALUES(3,2,"# Hello \n\n ## World",1594174524971,0,"你好3","https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c");

INSERT INTO `aplmu`.`course`(`id`,`child_course_num`,`author`,`duration`,`image_url`,`name`,`price`,`video_url`)VALUES(1,8,"理查德",4,"https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c","理查德的钢琴入门",100,"https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c");
INSERT INTO `aplmu`.`course`(`id`,`child_course_num`,`author`,`duration`,`image_url`,`name`,`price`,`video_url`)VALUES(2,9,"马克西姆",4,"https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c","21天精通克罗地亚狂想曲",200,"https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c");
INSERT INTO `aplmu`.`course`(`id`,`child_course_num`,`author`,`duration`,`image_url`,`name`,`price`,`video_url`)VALUES(3,8,"牛顿",4,"https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c","压不住棺材后的蹦迪教学",500,"https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c");