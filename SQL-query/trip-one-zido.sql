CREATE TABLE
	`member` (
		`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		`name` VARCHAR(100) NOT NULL,
		`login_id` VARCHAR(100) NOT NULL,
		`password` VARCHAR(50) NOT NULL,
		`question` BIGINT NOT NULL,
		`answer` VARCHAR(100) NOT NULL,
		`email` VARCHAR(100) NOT NULL,
		`phone_number` VARCHAR(20) NOT NULL,
		`address` VARCHAR(200) NOT NULL,
		`birth` TIMESTAMP NOT NULL,
		`gender` VARCHAR(10) NOT NULL,
		`naver_pairing` TINYINT NOT NULL DEFAULT 0,
		`naver_id` VARCHAR(100) NULL,
		`kakao_pairing` TINYINT NOT NULL DEFAULT 0,
		`kakao_id` VARCHAR(100) NULL,
		`profile` BLOB NULL
	);

CREATE TABLE
	`follow` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`follower` BIGINT NOT NULL,
		`following` BIGINT NOT NULL
	);

CREATE TABLE
	`security_questions` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`question` VARCHAR(100) NOT NULL
	);

CREATE TABLE
	`content` (
		`id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
		`type` VARCHAR(20) NOT NULL,
		`is_public` TINYINT NOT NULL,
		`title` VARCHAR(200) NOT NULL,
		`created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
		`modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
	);

CREATE TABLE
	`tour` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`loc_category` VARCHAR(50) NOT NULL,
		`address` VARCHAR(200) NOT NULL,
		`info` VARCHAR(300) NOT NULL
	);

CREATE TABLE
	`community` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`start_date` TIMESTAMP NOT NULL,
		`end_date` TIMESTAMP NOT NULL,
		`loc_category` VARCHAR(50) NOT NULL,
		`notice` VARCHAR(500) NOT NULL,
		`total` INT NOT NULL,
		`deadline` TIMESTAMP NOT NULL,
		`view_point` INT NOT NULL DEFAULT 0,
		`status` VARCHAR(20) NOT NULL
	);

CREATE TABLE
	`plan` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`start_date` TIMESTAMP NOT NULL,
		`end_date` TIMESTAMP NOT NULL,
		`loc_category` VARCHAR(50) NOT NULL,
		`review` VARCHAR(500) NOT NULL,
		`status` VARCHAR(20) NOT NULL,
		`view_point` INT NOT NULL DEFAULT 0,
		`grade` INT NOT NULL
	);

CREATE TABLE
	`spot` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`category` VARCHAR(50) NOT NULL,
		`start_date` TIMESTAMP NOT NULL,
		`end_date` TIMESTAMP NOT NULL,
		`loc_category` VARCHAR(50) NOT NULL,
		`address` VARCHAR(200) NOT NULL,
		`review` VARCHAR(500) NOT NULL,
		`view_point` INT NOT NULL DEFAULT 0,
		`grade` INT NOT NULL,
		`profile_id` BIGINT NOT NULL
	);

CREATE TABLE
	`plan_spot` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`plan_id` BIGINT NOT NULL,
		`spot_id` BIGINT NOT NULL
	);

CREATE TABLE
	`comment` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`comment` VARCHAR(300) NOT NULL,
		`content_id` BIGINT NOT NULL,
		`member_id` BIGINT NOT NULL
	);

CREATE TABLE
	`bookmark` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`member_id` BIGINT NOT NULL,
		`content_id` BIGINT NOT NULL
	);

CREATE TABLE
	`good` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`good` TINYINT NOT NULL,
		`member_id` BIGINT NOT NULL,
		`content_id` BIGINT NOT NULL
	);

CREATE TABLE
	`open_content` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`opened_at` TIMESTAMP NOT NULL,
		`member_id` BIGINT NOT NULL,
		`content_id` BIGINT NOT NULL
	);

CREATE TABLE
	`owner` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`own` VARCHAR(30) NOT NULL,
		`member_id` BIGINT NOT NULL,
		`content_id` BIGINT NOT NULL
	);

CREATE TABLE
	`pin` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`member_id` BIGINT NOT NULL,
		`content_id` BIGINT NOT NULL
	);

CREATE TABLE
	`photo` (
		`id` BIGINT NOT NULL PRIMARY KEY,
		`photo` BLOB NOT NULL,
		`content_id` BIGINT NOT NULL
	);

ALTER TABLE `follow` ADD CONSTRAINT `FK_member_TO_follow_1` FOREIGN KEY (`follower`) REFERENCES `member` (`id`);

ALTER TABLE `follow` ADD CONSTRAINT `FK_member_TO_follow_2` FOREIGN KEY (`following`) REFERENCES `member` (`id`);

ALTER TABLE `member` ADD CONSTRAINT `FK_security_questions_TO_member_1` FOREIGN KEY (`question`) REFERENCES `security_questions` (`id`);

ALTER TABLE `tour` ADD CONSTRAINT `FK_content_TO_tour_1` FOREIGN KEY (`id`) REFERENCES `content` (`id`);

ALTER TABLE `community` ADD CONSTRAINT `FK_content_TO_community_1` FOREIGN KEY (`id`) REFERENCES `content` (`id`);

ALTER TABLE `plan` ADD CONSTRAINT `FK_content_TO_plan_1` FOREIGN KEY (`id`) REFERENCES `content` (`id`);

ALTER TABLE `spot` ADD CONSTRAINT `FK_content_TO_spot_1` FOREIGN KEY (`id`) REFERENCES `content` (`id`);

ALTER TABLE `spot` ADD CONSTRAINT `FK_photo_TO_spot_1` FOREIGN KEY (`profile_id`) REFERENCES `photo` (`id`);

ALTER TABLE `plan_spot` ADD CONSTRAINT `FK_plan_spot_TO_plan_1` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`);

ALTER TABLE `plan_spot` ADD CONSTRAINT `FK_plan_spot_TO_spot_1` FOREIGN KEY (`spot_id`) REFERENCES `spot` (`id`);

ALTER TABLE `comment` ADD CONSTRAINT `FK_member_TO_comment_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);

ALTER TABLE `comment` ADD CONSTRAINT `FK_content_TO_comment_1` FOREIGN KEY (`id`) REFERENCES `content` (`id`);

ALTER TABLE `comment` ADD CONSTRAINT `FK_content_TO_comment_2` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`);

ALTER TABLE `bookmark` ADD CONSTRAINT `FK_member_TO_bookmark_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);

ALTER TABLE `bookmark` ADD CONSTRAINT `FK_content_TO_bookmark_1` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`);

ALTER TABLE `good` ADD CONSTRAINT `FK_member_TO_good_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);

ALTER TABLE `good` ADD CONSTRAINT `FK_content_TO_good_1` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`);

ALTER TABLE `open_content` ADD CONSTRAINT `FK_member_TO_open_content_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);

ALTER TABLE `open_content` ADD CONSTRAINT `FK_content_TO_open_content_1` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`);

ALTER TABLE `owner` ADD CONSTRAINT `FK_member_TO_owner_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);

ALTER TABLE `owner` ADD CONSTRAINT `FK_content_TO_owner_1` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`);

ALTER TABLE `pin` ADD CONSTRAINT `FK_member_TO_pin_1` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`);

ALTER TABLE `pin` ADD CONSTRAINT `FK_content_TO_pin_1` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`);

ALTER TABLE `photo` ADD CONSTRAINT `FK_content_TO_photo_1` FOREIGN KEY (`content_id`) REFERENCES `content` (`id`);