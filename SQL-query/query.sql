<회원 관리>
# 회원 가입
INSERT
INTO member(name, login_id, password, question, answer, email, phone_number, address, birth, gender)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

# 계정 연동을 통한 회원 가입
INSERT
INTO member(name, login_id, password, question, answer, email, phone_number, address, birth, gender, naver_pairing, naver_id, kakao_pairing, kakao_id)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

# 로그인
SELECT id
FROM member
WHERE login_id = ? AND password = ?;

# 아이디 찾기
SELECT login_id
FROM member
WHERE login_id = ? AND email = ?;

# 비밀번호 찾기/재등록
SELECT id
FROM member
WHERE login_id = ? AND name = ? AND email = ? AND question = ? AND answer = ?;

UPDATE member
SET password = ?
WHERE id = ?;


# 정보 변경
SELECT id
FROM member
WHERE id = ? AND password = ?;

UPDATE member
SET
    name = ?,
    login_id = ?,
    password = ?,
    question = ?,
    answer = ?,
    email = ?,
    phone_number = ?,
    address = ?,
    birth = ?,
    gender = ?
WHERE id = ?;


# 프로필 사진 변경
UPDATE member
SET profile = ?
WHERE id = ?;

# 회원탈퇴
SELECT id
FROM member
WHERE id = ?, password = ?;

DELETE FROM member
WHERE id = ?;


<회원 페이지>
# 팔로잉/팔로워
SELECT COUNT(following)
FROM follow, member
WHERE follow.following = member.id;

SELECT COUNT(follower)
FROM follow, member
WHERE follow.follower = member.id;

    - 목록
SELECT following
FROM follow, member
WHERE follow.following = member.id;

SELECT follower
FROM follow, member
WHERE follow.follower = member.id;


# 찜목록
-- SELECT id, profile
-- FROM schedule, content, bookmark, member
-- WHERE schedule.id = content.id AND bookmark.content_id = content_id AND bookmark.member_id = member.id
-- UNION
-- SELECT id, profile
-- FROM destination, content, bookmark, member
-- WHERE destination.id = content.id AND bookmark.content_id = content.id AND bookmark.member_id = member.id
-- UNION
-- SELECT id, picture
-- FROM tour_info, content, bookmark, member
-- WHERE tour_info.id = content.id AND bookmark.content_id = content.id AND bookmark.member_id = member.id;

# 게시글 목록
SELECT id, profile
FROM schedule
UNION
SELECT id, profile
FROM destination;


<설정페이지>
# 최근 본 게시글
-- SELECT id, profile
-- FROM schedule, content, open_content, member
-- WHERE schedule.id = content.id AND open_content.content_id = content.id AND open_content.member_id = member.id
-- ORDER BY open DESC
-- UNION
-- SELECT id, profile
-- FROM destination, content, open_content, member
-- WHERE destination.id = content.id AND open_content.content_id = content.id AND open_content.member_id = member.id
-- ORDER BY open DESC
-- UNION
-- SELECT id, picture
-- FROM tour_info, content, open_content, member
-- WHERE tour_info.id = content.id AND open_content.content_id = content.id AND open_content.member_id = member.id
-- ORDER BY open DESC;

<검색>
# 계정, 일정, 장소 검색
SELECT id, profile
FROM member
WHERE ? LIKE '%%'
UNION
SELECT id, profile
FROM schedule
WHERE ? LIKE '%%'
UNION
SELECT id, profile
FROM destination
WHERE ? LIKE '%%';

# 조건에 따른 일정 장소 검색
SELECT id, profile
FROM schedule
WHERE ? LIKE '%%' AND BETWEEN SUBDATE(NOW(), ?) AND NOW() AND loc_category = ?
UNION
SELECT id, profile
FROM destination
WHERE ? LIKE '%%' AND BETWEEN SUBDATE(NOW(), ?) AND NOW() AND loc_category = ? AND category = ?;


<커뮤니티>
# 커뮤니티 게시글 전체 조회
SELECT community.*, member.login_id, COUNT(owner.member_id) AS 참여인원
FROM community, content, owner, member
WHERE community.id = content.id AND owner.content_id = content.id  AND owner.member_id = member.id
      AND owner.content_id = community_id;

# 커뮤티니 게시글 상세 조회
SELECT community.*, member.login_id, COUNT(owner.member_id) AS 참여인원
FROM community, content, owner, member
WHERE community.id = content.id AND owner.content_id = content.id  AND owner.member_id = member.id
      AND owner.content_id = community_id;

# 커뮤니티 게시글 등록
INSERT
INTO community(title, start_date, end_date, loc_category, notice, recruitment, deadline)
VALUES (?, ?, ?, ?, ?, ?, ?);

# 커뮤니티 케시글 수정
UPDATE community
SET title = ?,
    start_date = ?,
    end_date = ?,
    loc_category = ?,
    notice = ?,
    recruitment = ?,
    deadline
WHERE id = ?;

# 커뮤니티 게시글 삭제
DELETE
FROM community
WHERE id = ?;

# 커뮤니티 게시글 검색
SELECT community.*, member.login_id, COUNT(owner.member_id) AS 참여인원
FROM community, content, owner, member
WHERE community.id = content.id AND owner.content_id = content.id  AND owner.member_id = member.id
      AND owner.content_id = community_id
	  AND ? LIKE '%%';

# 참여하기/취소하기
INSERT
INTO owner(own, member_id, content_id)
VALUES (?, ?, ?);


<관광정보>
# 관광정보 전체 조회
SELECT id, name, loc_category, picture
FROM tour_info
WHERE 0 = (SELECT COUNT(*)
            FROM bookmark
            WHERE content_id = tour_info.id);

SELECT id, name, loc_category, picture
FROM tour_info
WHERE 1 = (SELECT COUNT(*)
            FROM bookmark
            WHERE content_id = tour_info.id);

# 관광정보 상세 조회
SELECT *
FROM tour_info
WHERE id = ?;

# 관광정보 찜하기
INSERT
INTO bookmark(type, member_id, content_id)
VALUES (?, ?, ?);


<일정 게시글>
# 일정 게시글 조회
일정 + 동행인 / 댓글 / 좋아요 싫어요
SELECT schedule.*, id, login_id, profile
FROM schedule, member, owner, content
WHERE schedule.id = content.id AND owner.content_id = content.id AND owner.member_id = member.id

SELECT comment.*, login_id, profile
FROM comment, member, content, schedule
WHERE schedule.id = content.id AND comment.content_id = content.id AND comment.member_id = member.id

SELECT like
FROM schedule, like_unlike, member, content
WHERE schedule.id = content.id AND like_unlike.content_id = content.id AND like_unlike.member_id = member.id


# 일정 게시글 등록
INSERT
INTO schedule(title, start_date, end_date, loc_category, review, status, shared)
VALUES (?, ?, ?, ?, ?, ?, ?);

INSERT
INTO owner(own, member_id, content_id)
VALUES (?, ?, ?);

# 일정 게시글 수정
UPDATE schedule
SET
    title = ?,
    start_date = ?,
    end_date = ?,
    loc_category = ?,
    review = ?,
    status = ?,
    shared = ?
WHERE id = ?;

UPDATE owner
SET
    own = ?,
    member_id = ?,
    content_id = ?;

# 일정 게시글 삭제
DELETE
FROM schedule
WHERE id = ?;

<장소 게시글>
# 장소 게시글 조회
SELECT destination.*, comment.*, COUNT(comment.content_id) AS 댓글수, member.login_id , member.profile
FROM destination, comment, content, member
WHERE destination.id = content.id AND comment.content_id = content.id AND comment.member_id = member.id
      AND comment.content_id = destination.id;

# 장소 게시글 등록
INSERT
INTO destination(title, category, start_time, end_time, loc_category, address, review, shared, score, profile)
VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

# 장소 게시글 수정
UPDATE destination
SET
    title = ?,
    category = ?,
    start_time = ?,
    end_time = ?,
    loc_category = ?,
    address = ?,
    review = ?,
    shared = ?,
    score = ?,
    profile = ?
WHERE id = ?;

# 장소 게시글 삭제
DELETE
FROM destination
WHERE id = ?;


# 댓글 등록
INSERT
INTO comment(comment, content_id, member_id)
VALUES (?, ?, ?);

# 댓글 수정
UPDATE comment
SET
    comment = ?
WHERE id = ?;

# 댓글 삭제
DELETE
FROM comment
WHERE id = ?;

# 찜
INSERT
INTO bookmark(type, member_id, content_id)
VALUES (?, ?, ?);

# 좋아요/싫어요
INSERT
INTO like_unlike(like, member_id, content_id)
VALUES (?, ?, ?);