--장소게시글 조회
SELECT
  *
FROM
  destination
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
  picture
FROM
  picture
WHERE
  destination_id = ?;

--좋아요, 찜여부, 댓글  

--장소게시글 등록
INSERT INTO
  content (type) VALUE ('destination');

--INSERT 시 AI key 값 AI_ID에 저장
SELECT
  LAST_INSERT_ID ();

INSERT INTO
  destination (
    id,
    title,
    category,
    start_date,
    end_date,
    loc_category,
    address,
    review,
    shared,
    score,
    profile
  ) VALUE (AI_ID, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

INSERT INTO
  picture (picture, destination_id) VALUE (?, ?);

INSERT INTO
  owner (own, member_id, content_id) VALUE ('writer', ?, ?);

INSERT INTO
  owner (own, member_id, content_id) VALUE ('with', ?, ?);

--장소게시글 수정
UPDATE destination
SET
  title = ?,
  category = ?,
  start_date = ?,
  end_date = ?,
  loc_category = ?,
  address = ?,
  review = ?,
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

DELETE FROM picture
WHERE
  id = ?;

INSERT INTO
  picture (picture, destination_id) VALUE (?, ?);

--장소게시글 삭제
DELETE FROM destination
WHERE
  id = ?;