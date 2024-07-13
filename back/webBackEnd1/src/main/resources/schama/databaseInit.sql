create schema conference;

use conference;


CREATE TABLE `user`  (
     `id` int(11) NOT NULL AUTO_INCREMENT,
     `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
     `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
     `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
     `sex` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
     `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
     `address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE attendee (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          username VARCHAR(64) NOT NULL,
                          nickname VARCHAR(64),
                          password VARCHAR(64) NOT NULL,
                          phone VARCHAR(32),
                          sex CHAR(1),
                          `describe` VARCHAR(255),
                          gmt_create DATETIME DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO attendee (username, nickname, password, phone, sex, `describe`)
VALUES
    ('john_doe', 'John', 'johnpassword123', '1234567890', '1', 'A brief description about John.'),
    ('jane_doe', 'Jane', 'janepassword123', '0987654321', '0', 'A brief description about Jane.'),
    ('sam_smith', 'Sammy', 'samspassword123', '1122334455', '1', 'A brief description about Sammy.');

CREATE TABLE tenant (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(64) NOT NULL,
                        nickname VARCHAR(64),
                        password VARCHAR(64) NOT NULL,
                        phone VARCHAR(32),
                        sex CHAR(1),
                        `describe` VARCHAR(255),
                        gmt_create DATETIME DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO tenant (username, nickname, password, phone, sex, `describe`)
VALUES
    ('john_doe', 'John', 'johnpassword123', '1234567890', '1', 'A brief description about John.'),
    ('jane_doe', 'Jane', 'janepassword123', '0987654321', '0', 'A brief description about Jane.'),
    ('sam_smith', 'Sammy', 'samspassword123', '1122334455', '1', 'A brief description about Sammy.');

