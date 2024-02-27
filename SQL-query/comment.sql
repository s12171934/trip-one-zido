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