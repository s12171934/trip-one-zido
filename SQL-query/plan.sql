--일정게시글 조회
SELECT
  p.*,
  c.title,
  c.public,
  c.created_at
FROM
  plan p,
  content c
WHERE
  p.id = c.id
  AND id = ?;

SELECT
  o.own,
  m.id,
  m.login_id,
  m.profile
FROM
  owner o,
  member m
WHERE
  o.member_id = m.id
  AND o.content_id = ?;

SELECT
  c.id,
  c.title,
  s.start_time,
  s.end_time
FROM
  spot s,
  content c
WHERE
  s.id = c.id
  AND c.id in (
    SELECT
      spot_id
    FROM
      plan_spot
    WHERE
      plan_id = ?
  );

--일정게시글 등록
INSERT INTO
  content (type, public, title) VALUE ('plan', ?, ?);

--INSERT 시 AI key 값 AI_ID에 저장
SELECT
  LAST_INSERT_ID ();

INSERT INTO
  plan (
    id,
    start_date,
    end_date,
    loc_category,
    review,
    status,
    grade,
  ) VALUE (AI_ID, ?, ?, ?, ?, ?, ?);

INSERT INTO
  photo (photo, content_id) VALUE (?, ?);

--장소 등록 이후
INSERT INTO
  plan_spot (plan_id, spot_id) VALUE (?, ?);

INSERT INTO
  owner (own, member_id, content_id) VALUE ('writer', ?, ?);

INSERT INTO
  owner (own, member_id, content_id) VALUE ('with', ?, ?);

--일정게시글 수정
UPDATE content
SET
  title = ?,
  public = ?
WHERE
  id = ?;

UPDATE plan
SET
  start_date = ?,
  end_date = ?,
  loc_category = ?,
  review = ?,
  status = ?,
  grade = ?
WHERE
  id = ?;

UPDATE photo
SET
  photo = ?
WHERE
  content_id = ?;

DELETE FROM owner
WHERE
  content_id = ?
  AND own = 'with';

INSERT INTO
  owner (own, member_id, content_id) VALUE ('with', ?, ?);

DELETE FROM content
WHERE
  id = ?;

INSERT INTO
  plan_spot (plan_id, spot_id) VALUE (?, ?);

--일정게시글 삭제
DELETE FROM spot
WHERE
  id IN (
    SELECT
      spot_id
    FROM
      plan_spot
    WHERE
      plan_id = ?
  );

DELETE FROM content
WHERE
  id = ?;