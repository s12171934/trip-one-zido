--댓글조회
SELECT
  *
FROM
  comment;

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
DELETE FROM comment
WHERE
  id = ?;

--찜 등록하기
--TYPE에 content의 종류 저장
SELECT
  type
FROM
  content
WHERE
  id = ?;

INSERT INTO
  bookmark (type, content_id, member_id) VALUE (TYPE, ?, ?);

--찜 삭제하기
DELETE FROM bookmark
WHERE
  content_id = ?
  AND member_id = ?;

--좋아요/싫어요
DELETE FROM like_unlike
WHERE
  content_id = ?
  AND member_id = ?;

INSERT INTO
  like_unlike (like, member_id, content_id) VALUE (?, ?, ?);