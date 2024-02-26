--일정게시글 조회
SELECT
  *
FROM
  schedule
WHERE
  id = ?;

SELECT
  own,
  member_id
FROM
  owner
WHERE
  content_id = ?;

SELECT
  id,
  title,
  start_time,
  end_time
FROM
  destination
WHERE
  id in (
    SELECT
      destination_id
    FROM
      schedule_destination
    WHERE
      schedule_id = ?
  );

--좋아요, 찜여부, 댓글

--일정게시글 등록
INSERT INTO
  content (type) VALUE ('schedule');

--INSERT 시 AI key 값 AI_ID에 저장
SELECT
  LAST_INSERT_ID ();

INSERT INTO
  schedule (
    id,
    title,
    start_date,
    end_date,
    loc_category,
    review,
    status,
    shared,
    score,
    profile
  ) VALUE (AI_ID, ?, ?, ?, ?, ?, ?, ?, ?, ?);

--장소 등록 이후
INSERT INTO
  schedule_destination (schedule_id, destination_id) VALUE (?, ?);

INSERT INTO
  owner (own, member_id, content_id) VALUE ('writer', ?, ?);

INSERT INTO
  owner (own, member_id, content_id) VALUE ('with', ?, ?);

--일정게시글 수정
UPDATE schedule
SET
  title = ?,
  start_date = ?,
  end_date = ?,
  loc_category = ?,
  review = ?,
  status = ?,
  shared = ?,
  score = ?,
  profile = ?
WHERE
  id = ?;

DELETE FROM owner
WHERE
  content_id = ?
  AND own = 'with';

INSERT INTO
  owner (own, member_id, content_id) VALUE ('with', ?, ?);

DELETE FROM destination
WHERE
  id = ?;

INSERT INTO
  schedule_destination (schedule_id, destination_id) VALUE (?, ?);


--일정게시글 삭제
--장소게시글 삭제과정 필요

DELETE FROM schedule
WHERE
  id = ?;