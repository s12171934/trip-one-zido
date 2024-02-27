--커뮤니티 게시글 조회
SELECT
  c1.id,
  c2.title,
  o.member_id writer_id,
  m.login_id writer,
  c1.deadline,
  c1.view_point,
  c1.total,
  c1.status,
  (
    SELECT
      COUNT(*)
    FROM
      owner
    WHERE
      content_id = id
  ) participants_count
FROM
  community c1,
  owner o,
  member m,
  content c2
WHERE
  c1.id = o.content_id
  AND o.member_id = m.id
  AND o.own = 'writer'
  AND c2.id = c1.id
LIMIT
  10;

--커뮤니티 게시글 상세 조회
SELECT
  c1.*,
  c2.title,
  c2.created_at,
  c2.modified_at
FROM
  community c1,
  content c2
WHERE
  c1.id = c2.id
  AND c1.id = ?;

SELECT
  o.member_id,
  o.own,
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
  content (type, title) VALUE ('community', ?);

--INSERT 시 AI key 값 AI_ID에 저장
SELECT
  LAST_INSERT_ID ();

INSERT INTO
  community (
    id,
    start_date,
    end_date,
    loc_category,
    notice,
    total,
    deadline
  ) VALUE (AI_ID, ?, ?, ?, ?, ?, ?);

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

--커뮤니티 게시글 검색
-- 제목
SELECT
    c1.id,
    c2.title,
    o.member_id,
    m.login_id,
    c1.deadline,
    c1.view_point,
    (
        SELECT
            COUNT(*)
        FROM
            owner
        WHERE
            content_id = id
    ) participants_count,
    c1.total,
    c1.status
FROM
    community c1,
    content c2,
    member m,
    owner o
WHERE
    c1.id = o.content_id
    AND o.member_id = m.id
    AND o.own = 'writer'
    AND c1.id = c2.id
    AND c2.title LIKE ?;

-- 작성자
SELECT
    c1.id,
    c2.title,
    o.member_id,
    m.login_id,
    c1.deadline,
    c1.view_point,
    (
        SELECT
            COUNT(*)
        FROM
            owner
        WHERE
            content_id = id
    ) participants_count,
    c1.total,
    c1.status
FROM
    community c1,
    content c2,
    member m,
    owner o
WHERE
    c1.id = o.content_id
    AND o.member_id = m.id
    AND o.own = 'writer'
    AND c1.id = c2.id
    AND m.login_id LIKE ?;

--커뮤니티 여행 참여/취소하기
INSERT INTO
  owner (own, member_id, content_id) VALUE ('participants', ?, ?);

DELETE FROM owner
WHERE
  member_id = ?
  AND content_id = ?;