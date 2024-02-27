--댓글조회
SELECT
  comment.*,
  content.created_at
FROM
  comment
WHERE
  comment.id = content.id;

--댓글등록
INSERT INTO
  content (type) VALUE ('comment');

--INSERT 시 AI key 값 AI_ID에 저장
SELECT
  LAST_INSERT_ID ();

INSERT INTO
  comment (id, comment, content_id, member_id) VALUE (AI_ID, ?, ?, ?);

--댓글수정
UPDATE comment
SET
  comment = ?
WHERE
  id = ?;

--댓글삭제
DELETE FROM content
WHERE
  id = ?;

--찜 등록하기
--TYPE에 content의 종류 저장
INSERT INTO
  bookmark (content_id, member_id) VALUE (?, ?);

--찜 삭제하기
DELETE FROM bookmark
WHERE
  content_id = ?
  AND member_id = ?;

--좋아요/싫어요
UPDATE good
SET
  good = NOT good
WHERE
  member_id = ?
  AND content_id = ?;

DELETE FROM good
WHERE
  content_id = ?
  AND member_id = ?;

INSERT INTO
  good (good, member_id, content_id) VALUE (?, ?, ?);

--좋아요 조회
SELECT COUNT(*)
FROM good
WHERE
  content_id = ?
  AND good = 1;

--찜횟수 조회
SELECT COUNT(*)
FROM bookmark
WHERE
  content_id = ?;

--찜여부 조회
SELECT COUNT(*)
FROM bookmark
WHERE
  content_id = ?
  AND member_id = ?;