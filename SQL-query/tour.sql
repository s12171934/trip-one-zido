--관광정보 전체 조회
SELECT
  id,
  c.title,
  p.photo,
  address,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = ?
  ) bookmark_count,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = ?
      AND member_id = ?
  ) my_bookmark
FROM
  tour t,
  content c,
  photo p
WHERE
  tour.id = content.id
  AND tour.id = photo.content_id;

--관광정보 상세 조회
SELECT
  t.*,
  c.title,
  p.photo,
FROM
  tour t,
  content c,
  photo p
WHERE
  id = ?
  AND tour.id = content.id
  AND tour.id = photo.content_id;

--API를 통한 관광정보 등록
INSERT INTO
  content (type, title) VALUE ('tour', ?);

--INSERT 시 AI key 값 AI_ID에 저장
SELECT
  LAST_INSERT_ID ();

INSERT INTO
  tour (id, loc_category, address, info) VALUE (AI_ID, ?, ?, ?);

INSERT INTO
  photo (photo, content_id) VALUE (?, ?);