--관광정보 전체 조회
SELECT
  id,
  name,
  picture,
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
  tour_info;

--관광정보 상세 조회
SELECT
  *
FROM
  tour_info;