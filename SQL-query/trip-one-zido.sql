CREATE TABLE `member` (
	`id`	BIGINT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name`	VARCHAR(100)	NOT NULL,
	`login_id`	VARCHAR(100)	NOT NULL,
	`password`	VARCHAR(50)	NOT NULL,
	`question`	BIGINT	NOT NULL,
	`answer`	VARCHAR(100)	NOT NULL,
	`email`	VARCHAR(100)	NOT NULL,
	`phone_number`	VARCHAR(20)	NOT NULL,
	`address`	VARCHAR(200)	NOT NULL,
	`birth`	TIMESTAMP	NOT NULL,
	`gender`	VARCHAR(10)	NOT NULL,
	`naver_pairing`	TINYINT	NOT NULL	DEFAULT 0,
	`naver_id`	VARCHAR(100)	NULL,
	`kakao_pairing`	TINYINT	NOT NULL	DEFAULT 0,
	`kakao_id`	VARCHAR(100)	NULL,
	`profile`	BLOB	NULL
);

CREATE TABLE `community` (
	`id`	BIGINT	NOT NULL,
	`start_date`	TIMESTAMP	NOT NULL,
	`end_date`	TIMESTAMP	NOT NULL,
	`loc_category`	VARCHAR(50)	NOT NULL,
	`notice`	VARCHAR(500)	NOT NULL,
	`total`	INT	NOT NULL,
	`deadline`	TIMESTAMP	NOT NULL,
	`view_point`	INT	NOT NULL	DEFAULT 0,
	`status`	VARCHAR(20)	NOT NULL
);

CREATE TABLE `tour` (
	`id`	BIGINT	NOT NULL,
	`loc_category`	VARCHAR(50)	NOT NULL,
	`address`	VARCHAR(200)	NOT NULL,
	`info`	VARCHAR(300)	NOT NULL
);

CREATE TABLE `plan` (
	`id`	BIGINT	NOT NULL,
	`start_date`	TIMESTAMP	NOT NULL,
	`end_date`	TIMESTAMP	NOT NULL,
	`loc_category`	VARCHAR(50)	NOT NULL,
	`review`	VARCHAR(500)	NOT NULL,
	`status`	VARCHAR(20)	NOT NULL,
	`view_point`	INT	NOT NULL	DEFAULT 0,
	`grade`	INT	NOT NULL
);

CREATE TABLE `spot` (
	`id`	BIGINT	NOT NULL,
	`category`	VARCHAR(50)	NOT NULL,
	`start_date`	TIMESTAMP	NOT NULL,
	`end_date`	TIMESTAMP	NOT NULL,
	`loc_category`	VARCHAR(50)	NOT NULL,
	`address`	VARCHAR(200)	NOT NULL,
	`review`	VARCHAR(500)	NOT NULL,
	`view_point`	INT	NOT NULL	DEFAULT 0,
	`grade`	INT	NOT NULL,
	`profile_id`	BIGINT	NOT NULL
);

CREATE TABLE `comment` (
	`id`	BIGINT	NOT NULL,
	`comment`	VARCHAR(300)	NOT NULL,
	`content_id`	BIGINT	NOT NULL,
	`member_id`	BIGINT	NOT NULL
);

CREATE TABLE `follow` (
	`id`	BIGINT	NOT NULL,
	`follower`	BIGINT	NOT NULL,
	`following`	BIGINT	NOT NULL
);

CREATE TABLE `bookmark` (
	`id`	BIGINT	NOT NULL,
	`member_id`	BIGINT	NOT NULL,
	`content_id`	BIGINT	NOT NULL
);

CREATE TABLE `content` (
	`id`	BIGINT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`type`	VARCHAR(20)	NOT NULL,
	`public`	TINYINT	NOT NULL,
	`title`	VARCHAR(200)	NOT NULL,
	`created_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
	`modified_at`	TIMESTAMP	NOT NULL	DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `open_content` (
	`id`	BIGINT	NOT NULL,
	`opened_at`	TIMESTAMP	NOT NULL,
	`member_id`	BIGINT	NOT NULL,
	`content_id`	BIGINT	NOT NULL
);

CREATE TABLE `photo` (
	`id`	BIGINT	NOT NULL,
	`photo`	BLOB	NOT NULL,
	`content_id`	BIGINT	NOT NULL
);

CREATE TABLE `plan_spot` (
	`id`	BIGINT	NOT NULL,
	`plan_id`	BIGINT	NOT NULL,
	`spot_id`	BIGINT	NOT NULL
);

CREATE TABLE `good` (
	`id`	BIGINT	NOT NULL,
	`good`	TINYINT	NOT NULL,
	`member_id`	BIGINT	NOT NULL,
	`content_id`	BIGINT	NOT NULL
);

CREATE TABLE `owner` (
	`id`	BIGINT	NOT NULL,
	`own`	VARCHAR(30)	NOT NULL,
	`member_id`	BIGINT	NOT NULL,
	`content_id`	BIGINT	NOT NULL
);

CREATE TABLE `pin` (
	`id`	BIGINT	NOT NULL,
	`content_id`	BIGINT	NOT NULL
);

CREATE TABLE `security_questions` (
	`id`	BIGINT	NOT NULL,
	`question`	VARCHAR(100)	NOT NULL
);

ALTER TABLE `community` ADD CONSTRAINT `PK_COMMUNITY` PRIMARY KEY (
	`id`
);

ALTER TABLE `tour` ADD CONSTRAINT `PK_TOUR` PRIMARY KEY (
	`id`
);

ALTER TABLE `plan` ADD CONSTRAINT `PK_PLAN` PRIMARY KEY (
	`id`
);

ALTER TABLE `spot` ADD CONSTRAINT `PK_SPOT` PRIMARY KEY (
	`id`
);

ALTER TABLE `comment` ADD CONSTRAINT `PK_COMMENT` PRIMARY KEY (
	`id`
);

ALTER TABLE `follow` ADD CONSTRAINT `PK_FOLLOW` PRIMARY KEY (
	`id`
);

ALTER TABLE `bookmark` ADD CONSTRAINT `PK_BOOKMARK` PRIMARY KEY (
	`id`
);

ALTER TABLE `open_content` ADD CONSTRAINT `PK_OPEN_CONTENT` PRIMARY KEY (
	`id`
);

ALTER TABLE `photo` ADD CONSTRAINT `PK_PHOTO` PRIMARY KEY (
	`id`
);

ALTER TABLE `plan_spot` ADD CONSTRAINT `PK_PLAN_SPOT` PRIMARY KEY (
	`id`
);

ALTER TABLE `good` ADD CONSTRAINT `PK_GOOD` PRIMARY KEY (
	`id`
);

ALTER TABLE `owner` ADD CONSTRAINT `PK_OWNER` PRIMARY KEY (
	`id`
);

ALTER TABLE `pin` ADD CONSTRAINT `PK_PIN` PRIMARY KEY (
	`id`
);

ALTER TABLE `security_questions` ADD CONSTRAINT `PK_SECURITY_QUESTIONS` PRIMARY KEY (
	`id`
);

ALTER TABLE `community` ADD CONSTRAINT `FK_content_TO_community_1` FOREIGN KEY (
	`id`
)
REFERENCES `content` (
	`id`
);

ALTER TABLE `tour` ADD CONSTRAINT `FK_content_TO_tour_1` FOREIGN KEY (
	`id`
)
REFERENCES `content` (
	`id`
);

ALTER TABLE `plan` ADD CONSTRAINT `FK_content_TO_plan_1` FOREIGN KEY (
	`id`
)
REFERENCES `content` (
	`id`
);

ALTER TABLE `spot` ADD CONSTRAINT `FK_content_TO_spot_1` FOREIGN KEY (
	`id`
)
REFERENCES `content` (
	`id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_content_TO_comment_1` FOREIGN KEY (
	`id`
)
REFERENCES `content` (
	`id`
);

