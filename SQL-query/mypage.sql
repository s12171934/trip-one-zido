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
  content c
WHERE
  b.content_id = c.id
  AND c.public = 1
  AND b.member_id = ?;

--찜 목록 - 관광지
SELECT
  t.id,
  c.title,
  t.picture,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = c.id
  ) bookmark_count
FROM
  tour_info t,
  content c
WHERE
  t.id = c.id
  AND t.id IN (
    SELECT
      content_id
    FROM
      bookmark
    WHERE
      member_id = ?
  );

--찜 목록 - 일정/장소
SELECT
  c.id,
  c.title,
  CASE
    WHEN c.type = 'spot' THEN s.grade
    WHEN c.type = 'plan' THEN p.grade
    ELSE 0
  END AS grade,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = c.id
  ) bookmark_count,
  (
    SELECT
      COUNT(*)
    FROM
      good
    WHERE
      content_id = c.id
      AND good = 1
  ) good_count,
FROM
  spot s,
  plan p,
  content c
WHERE
  s.id = c.id
  AND p.id = c.id
  AND (
    c.public = 1
    OR c.id IN (
      SELECT
        member_id
      FROM
        owner
      WHERE
        content_id = c.id
    )
  )
  AND c.id IN (
    SELECT
      content_id
    FROM
      owner
    WHERE
      member_id = ?
  )
LIMIT
  6;

--찜목록 숫자표시 - 관광지
SELECT
  COUNT(*)
FROM
  bookmark b,
  content c
WHERE
  b.content_id = c.id
  AND c.type = 'tour'
  AND b.member_id = ?;

--찜목록 숫자표시 - 일정/장소
SELECT
  COUNT(*)
FROM
  bookmark b,
  content c
WHERE
  b.content_id = c.id
  AND c.public = 1
  AND c.type != 'tour'
  AND b.member_id = ?;

--게시물 목록 - 일정
SELECT
  c.id,
  c.title,
  p.grade,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = c.id
  ) bookmark_count,
  (
    SELECT
      COUNT(*)
    FROM
      good
    WHERE
      content_id = c.id
      AND good = 1
  ) good_count,
  p.view_potint,
  c.created_at,
  (
    SELECT
      COUNT(*)
    FROM
      pin
    WHERE
      content_id = ?
  ) pin
FROM
  plan p,
  content c
WHERE
  p.id = c.id
  AND (
    c.public = 1
    OR c.id IN (
      SELECT
        member_id
      FROM
        owner
      WHERE
        content_id = c.id
    )
  )
  AND c.id IN (
    SELECT
      content_id
    FROM
      owner
    WHERE
      member_id = ?
  )
ORDER BY
pin, created_at
LIMIT
  6;

--게시물 목록 - 장소
SELECT
  c.id,
  c.title,
  s.grade,
  (
    SELECT
      COUNT(*)
    FROM
      bookmark
    WHERE
      content_id = c.id
  ) bookmark_count,
  (
    SELECT
      COUNT(*)
    FROM
      good
    WHERE
      content_id = c.id
      AND good = 1
  ) good_count,
  p.view_potint,
  c.created_at,
  (
    SELECT
      COUNT(*)
    FROM
      pin
    WHERE
      content_id = ?
  ) pin
FROM
  spot s,
  content c
WHERE
  s.id = c.id
  AND (
    c.public = 1
    OR c.id IN (
      SELECT
        member_id
      FROM
        owner
      WHERE
        content_id = c.id
    )
  )
  AND c.id IN (
    SELECT
      content_id
    FROM
      owner
    WHERE
      member_id = ?
  )
ORDER BY
pin, created_at
LIMIT
  6;

--최근 본 게시글 목록
SELECT
  c.id,
  c.title,
  p.profile,
  o.open
FROM
  open_content o,
  content c,
  photo p
WHERE
  o.content_id = c.id
  AND c.id = p.content_id
  AND c.public = 1
  AND (
    c.type != 'spot'
    OR p.id IN (
      SELECT
        profile_id
      FROM
        spot
    )
  )
ORDER BY
  open
LIMIT
  12;