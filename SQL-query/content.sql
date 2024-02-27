--찜 목록 총 개수
SELECT
  COUNT(*)
FROM
  bookmark b,
  content c
WHERE
  b.content_id = c.id
  AND c.is_public = 1
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
  tour t,
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
  c.photo
FROM
  spot s,
  plan p,
  content c
WHERE
  s.id = c.id
  AND p.id = c.id
  AND (
    c.is_public = 1
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
  AND c.is_public = 1
  AND c.type != 'tour'
  AND b.member_id = ?;

--찜 등록하기
--TYPE에 content의 종류 저장
INSERT INTO
  bookmark (content_id, member_id) VALUE (?, ?);

--찜 삭제하기
DELETE FROM bookmark
WHERE
  content_id = ?
  AND member_id = ?;

--찜횟수 조회
SELECT
  COUNT(*)
FROM
  bookmark
WHERE
  content_id = ?;

--찜여부 조회
SELECT
  COUNT(*)
FROM
  bookmark
WHERE
  content_id = ?
  AND member_id = ?;


--좋아요/싫어요
UPDATE good
SET
  good = NOT good
WHERE
  member_id = ?
  AND content_id = ?;

DELETE FROM good
WHERE
  content_id = ?
  AND member_id = ?;

INSERT INTO
  good (good, member_id, content_id) VALUE (?, ?, ?);

--좋아요 조회
SELECT
  COUNT(*)
FROM
  good
WHERE
  content_id = ?
  AND good = 1;


--게시물 고정 등록
INSERT INTO
  pin (member_id, content_id) VALUE (?, ?);

--게시물 고정 취소
DELETE FROM pin
WHERE
  member_id = ?
  AND content_id = ?;
  

--최근 본 게시글 목록
SELECT
  c.id,
  c.title,
  p.photo,
  o.opened_at
FROM
  open_content o,
  content c,
  photo p
WHERE
  o.content_id = c.id
  AND c.id = p.content_id
  AND c.is_public = 1
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