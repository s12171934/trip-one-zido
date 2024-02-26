--커뮤니티 게시글 조회
SELECT
  c.id,
  c.title,
  o.member_id writer_id,
  m.login_id writer
  c.deadline,
  c.view_point,
  c.total,
  c.status,
  (
    SELECT
      COUNT(*)
    FROM
      owner
    WHERE
      content_id = id
  ) total_count
FROM
  community c,
  owner o,
  member m
WHERE
  c.id = o.content_id
  AND o.member_id = m.id
  AND o.own = 'writer'
LIMIT
  10;

--커뮤니티 게시글 상세 조회
SELECT
  *
FROM
  community
WHERE
  id = ?;

SELECT
  o.member_id,
  o.own,
  m.name,
  m.login_id,
  m.profile
FROM
  member m,
  owner o
WHERE
  m.id = o.member_id
  AND o.content_id = ?;

--커뮤니티 게시글 등록
INSERT INTO
  content (type) VALUE ('community');

--INSERT 시 AI key 값 AI_ID에 저장
SELECT
  LAST_INSERT_ID ();

INSERT INTO
  community (
    id,
    title,
    start_date,
    end_date,
    loc_category,
    notice,
    total,
    deadline
  ) VALUE (AI_ID, ?, ?, ?, ?, ?, ?, ?);

INSERT INTO
  owner (own, member_id, content_id) VALUE ('writer', ?, AI_ID);

--커뮤니티 게시글 수정
UPDATE community
SET
  start_date = ?,
  end_date = ?,
  loc_category = ?,
  notice = ?,
  total = ?,
  deadline = ?
WHERE
  id = ?;

UPDATE content
SET
  title = ?
WHERE
  id = ?;


--커뮤니티 게시글 삭제
DELETE FROM content
WHERE
  id = ?;

--커뮤니티 여행 참여/취소하기
INSERT INTO
  owner (own, member_id, content_id) VALUE ('participants', ?, ?);

DELETE FROM owner
WHERE
  member_id = ?
  AND content_id = ?;