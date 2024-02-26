--팔로잉
INSERT INTO
  follow (follower, following) VALUE (?, ?);

--언팔
DELETE FROM follow
WHERE
  follower = ?
  AND following = ?;

--팔로잉 숫자
SELECT
  COUNT(*)
FROM
  follow
WHERE
  follower = ?;

--팔로잉 목록
SELECT
  id,
  name,
  login_id,
  profile
FROM
  member
WHERE
  id IN (
    SELECT
      following
    FROM
      follow
    WHERE
      follower = ?
  );

--팔로워 숫자
SELECT
  COUNT(*)
FROM
  follow
WHERE
  following = ?;

--팔로워 목록
SELECT
  id,
  name,
  login_id,
  profile
FROM
  member
WHERE
  id IN (
    SELECT
      follower
    FROM
      follow
    WHERE
      following = ?
  );

--찜 숫자
SELECT
  COUNT(*)
FROM
  bookmark b,
  (
    SELECT
      id,
      shared
    FROM
      schedule
    UNION
    SELECT
      id,
      shared
    FROM
      destination
  ) share s
WHERE
  b.content_id = s.id
  AND (
    s.shared = 1
    OR s.shared is NULL
  )
  AND member_id = ?;

--찜 목록 - 관광지
SELECT
  id,
  name,
  picture
FROM
  tour_info
WHERE
  id IN (
    SELECT
      content_id
    FROM
      bookmark
    WHERE
      member = ?
  );  

--찜 목록 - 일정
SELECT
  id,
  title,
  score,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = id
  ) bookmark_count
FROM
  schedule
WHERE
  shared = 1
  AND id IN (
    SELECT
      content_id
    FROM
      bookmark
    WHERE
      member = ?
  )
UNION
SELECT
  id,
  title,
  address,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = id
  ) bookmark_count
FROM
  destination
WHERE
  shared = 1
  AND id IN (
    SELECT
      content_id
    FROM
      bookmark
    WHERE
      member = ?
  );

--찜목록에서 찜숫자표시 찜삭제  

--게시물 목록 - 일정
SELECT
  id,
  title,
  score,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = id
  ) bookmark_count
FROM
  schedule s
WHERE
  (
    shared = 1
    OR id IN (
      SELECT
        member_id
      FROM
        owner
      WHERE
        content_id = s.id
    )
  )
  AND id IN (
    SELECT
      content_id
    FROM
      owner
    WHERE
      member_id = ?
  );

--게시물 목록 - 장소
SELECT
  id,
  title,
  address,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = id
  ) bookmark_count
FROM
  destination d
WHERE
  (
    shared = 1
    OR id IN (
      SELECT
        member_id
      FROM
        owner
      WHERE
        content_id = d.id
    )
  )
  AND id IN (
    SELECT
      content_id
    FROM
      owner
    WHERE
      member_id = ?
  );

--정렬방식, 핀고정활용

--최근 본 게시글 목록
SELECT
  conts.id,
  conts.title,
  conts.profile,
  o.open
FROM
  open_content o,
  (
    SELECT
      id content_id,
      title,
      profile
    FROM
      schedule
    WHERE
      member_id = ?
      AND shared = 1
    UNION
    SELECT
      id,
      title,
      profile
    FROM
      destination
    WHERE
      member_id = ?
      AND shared = 1
    UNION
    SELECT
      id,
      title,
      picture
    FROM
      tour_info
    WHERE
      member_id = ?
  ) conts
WHERE
  o.content_id = conts.content_id
ORDER BY
  open;