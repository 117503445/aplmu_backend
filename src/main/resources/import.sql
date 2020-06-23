INSERT INTO user (ID, USERNAME, PASSWORD, avatar, LASTNAME, EMAIL, ACTIVATED) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c', 'admin', 'admin@admin.com', 1);
INSERT INTO user (ID, USERNAME, PASSWORD, avatar, LASTNAME, EMAIL, ACTIVATED) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c', 'user', 'enabled@user.com', 1);
INSERT INTO user (ID, USERNAME, PASSWORD, avatar, LASTNAME, EMAIL, ACTIVATED) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'https://qph.fs.quoracdn.net/main-qimg-584b2d27360ea867c70d14214767574c', 'user', 'disabled@user.com', 0);

INSERT INTO authority (NAME) VALUES ('ROLE_USER');
INSERT INTO authority (NAME) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_USER');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (2, 'ROLE_USER');
INSERT INTO user_authority (USER_ID, AUTHORITY_NAME) VALUES (3, 'ROLE_USER');