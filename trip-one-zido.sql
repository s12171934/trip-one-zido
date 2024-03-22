DROP TABLE IF EXISTS `member`;
DROP TABLE IF EXISTS `community`;
DROP TABLE IF EXISTS `tour`;
DROP TABLE IF EXISTS `plan`;
DROP TABLE IF EXISTS `spot`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `follow`;
DROP TABLE IF EXISTS `bookmark`;
DROP TABLE IF EXISTS `content`;
DROP TABLE IF EXISTS `open_content`;
DROP TABLE IF EXISTS `photo`;
DROP TABLE IF EXISTS `plan_spot`;
DROP TABLE IF EXISTS `good`;
DROP TABLE IF EXISTS `owner`;
DROP TABLE IF EXISTS `security_questions`;

CREATE TABLE `member` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `login_id` VARCHAR(100) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `question` BIGINT NOT NULL,
  `answer` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(20) NOT NULL,
  `zipcode` VARCHAR(20) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `address2` VARCHAR(200) NOT NULL,
  `birth` DATE NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `naver_pairing` TINYINT NOT NULL DEFAULT 0,
  `naver_id` VARCHAR(100) NULL,
  `kakao_pairing` TINYINT NOT NULL DEFAULT 0,
  `kakao_id` VARCHAR(100) NULL,
  `profile` BLOB NULL
);

CREATE TABLE `community` (
  `id` BIGINT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `loc_category` VARCHAR(50) NOT NULL,
  `notice` VARCHAR(500) NOT NULL,
  `total` INT NOT NULL,
  `deadline` DATE NOT NULL,
  `view_point` INT NOT NULL DEFAULT 0,
  `status` VARCHAR(20) NOT NULL
);

CREATE TABLE `tour` (
  `id` BIGINT NOT NULL,
  `loc_category` VARCHAR(50) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `info` VARCHAR(300) NOT NULL
);

CREATE TABLE `plan` (
  `id` BIGINT NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `loc_category` VARCHAR(50) NOT NULL,
  `review` VARCHAR(500) NOT NULL,
  `status` VARCHAR(20) NOT NULL,
  `view_point` INT NOT NULL DEFAULT 0,
  `grade` INT NOT NULL
);

CREATE TABLE `spot` (
  `id` BIGINT NOT NULL,
  `category` VARCHAR(50) NOT NULL,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `loc_category` VARCHAR(50) NOT NULL,
  `address` VARCHAR(200) NOT NULL,
  `address2` VARCHAR(200) NOT NULL,
  `review` VARCHAR(500) NOT NULL,
  `view_point` INT NOT NULL DEFAULT 0,
  `grade` INT NOT NULL,
  `profile_id` BIGINT NOT NULL
);

CREATE TABLE `comment` (
  `id` BIGINT NOT NULL,
  `comment` VARCHAR(300) NOT NULL,
  `content_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL
);

CREATE TABLE `follow` (
  `id` BIGINT NOT NULL,
  `follower` BIGINT NOT NULL,
  `following` BIGINT NOT NULL
);

CREATE TABLE `bookmark` (
  `id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `content_id` BIGINT NOT NULL
);

CREATE TABLE `content` (
  `id` BIGINT NOT NULL,
  `type` VARCHAR(20) NOT NULL,
  `visibility` TINYINT NOT NULL,
  `title` VARCHAR(200) NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE `open_content` (
  `id` BIGINT NOT NULL,
  `opened_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `member_id` BIGINT NOT NULL,
  `content_id` BIGINT NOT NULL
);



CREATE TABLE `photo` (
  `id` BIGINT NOT NULL,
  `photo` LONGBLOB NOT NULL,
  `content_id` BIGINT NOT NULL
);

CREATE TABLE `plan_spot` (
  `id` BIGINT NOT NULL,
  `plan_id` BIGINT NOT NULL,
  `spot_id` BIGINT NOT NULL
);

CREATE TABLE `good` (
  `id` BIGINT NOT NULL,
  `good` TINYINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `content_id` BIGINT NOT NULL
);

CREATE TABLE `owner` (
  `id` BIGINT NOT NULL,
  `own` VARCHAR(30) NOT NULL,
  `member_id` BIGINT NOT NULL,
  `content_id` BIGINT NOT NULL
);

CREATE TABLE `security_questions` (
  `id` BIGINT NOT NULL,
  `question` VARCHAR(100) NOT NULL
);

ALTER TABLE `member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
  `id`
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

ALTER TABLE `content` ADD CONSTRAINT `PK_CONTENT` PRIMARY KEY (
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

ALTER TABLE `security_questions` ADD CONSTRAINT `PK_SECURITY_QUESTIONS` PRIMARY KEY (
  `id`
);

ALTER TABLE `bookmark` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `content` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `follow` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `good` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `member` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `open_content` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `owner` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `photo` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `plan_spot` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `security_questions` MODIFY `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `community` ADD CONSTRAINT `FK_content_TO_community_1` FOREIGN KEY (
  `id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `tour` ADD CONSTRAINT `FK_content_TO_tour_1` FOREIGN KEY (
  `id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `plan` ADD CONSTRAINT `FK_content_TO_plan_1` FOREIGN KEY (
  `id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `spot` ADD CONSTRAINT `FK_content_TO_spot_1` FOREIGN KEY (
  `id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `spot` ADD CONSTRAINT `FK_photo_TO_spot_1` FOREIGN KEY (
  `profile_id`
)
REFERENCES `photo` (
  `id`
);

ALTER TABLE `photo` ADD CONSTRAINT `FK_spot_TO_photo_1` FOREIGN KEY (
  `content_id`
)
REFERENCES `spot` (
  `id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_content_TO_comment_1` FOREIGN KEY (
  `id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `comment` ADD CONSTRAINT `FK_content_TO_comment_2` FOREIGN KEY (
  `content_id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `comment` ADD CONSTRAINT `FK_member_TO_comment_1` FOREIGN KEY (
  `member_id`
)
REFERENCES `member` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `follow` ADD CONSTRAINT `FK_member_TO_follow_follower` FOREIGN KEY (
  `follower`
)
REFERENCES `member` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `follow` ADD CONSTRAINT `FK_member_TO_follow_following` FOREIGN KEY (
  `following`
)
REFERENCES `member` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `member` ADD CONSTRAINT `FK_security_question_TO_member_1` FOREIGN KEY (
  `question`
)
REFERENCES `security_questions` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `open_content` ADD CONSTRAINT `FK_member_TO_open_content_1` FOREIGN KEY (
  `member_id`
)
REFERENCES `member` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `open_content` ADD CONSTRAINT `FK_content_TO_open_content_1` FOREIGN KEY (
  `content_id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `good` ADD CONSTRAINT `FK_member_TO_good_1` FOREIGN KEY (
  `member_id`
)
REFERENCES `member` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `good` ADD CONSTRAINT `FK_content_TO_good_1` FOREIGN KEY (
  `content_id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `owner` ADD CONSTRAINT `FK_member_TO_owner_1` FOREIGN KEY (
  `member_id`
)
REFERENCES `member` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `owner` ADD CONSTRAINT `FK_content_TO_owner_1` FOREIGN KEY (
  `content_id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `bookmark` ADD CONSTRAINT `FK_member_TO_bookmark_1` FOREIGN KEY (
  `member_id`
)
REFERENCES `member` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `bookmark` ADD CONSTRAINT `FK_content_TO_bookmark_1` FOREIGN KEY (
  `content_id`
)
REFERENCES `content` (
  `id`
)
ON DELETE CASCADE;

ALTER TABLE `follow` ADD CONSTRAINT `UK_follow_1` UNIQUE KEY (
  `follower`, `following`
);

ALTER TABLE `open_content` ADD CONSTRAINT `UK_open_content_1` UNIQUE KEY (
  `member_id`, `content_id`
);

ALTER TABLE `good` ADD CONSTRAINT `UK_good_1` UNIQUE KEY (
  `member_id`, `content_id`
);

ALTER TABLE `owner` ADD CONSTRAINT `UK_owner_1` UNIQUE KEY (
  `member_id`, `content_id`
);

ALTER TABLE `bookmark` ADD CONSTRAINT `UK_bookmark_1` UNIQUE KEY (
  `member_id`, `content_id`
);

ALTER TABLE `plan_spot` ADD CONSTRAINT `UK_plan_spot_1` UNIQUE KEY (
  `plan_id`, `spot_id`
);

ALTER TABLE `follow` ADD CONSTRAINT `CH_follow_1` CHECK (
  `follower` != `following`
);

