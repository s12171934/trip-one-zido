-- 검색 페이지
--계정, 일정, 장소 키워드 검색
-- 계정
SELECT
    id,
    login_id,
    profile
FROM
    member
WHERE
    login_id LIKE ?;

-- 일정
SELECT
    p1.id,
    c.title,
    p2.photo
FROM
    plan p1,
    content c,
    photo p2
WHERE
    p1.id = c.id
    AND c.id = p2.content_id
    AND c.title LIKE ?;

-- 장소
SELECT
    s.id,
    c.title,
    s.profile_id
FROM
    spot s,
    content c
WHERE
    s.id = c.id
    AND c.title LIKE ?;

--조건에 따른 일정, 장소 상세 검색
-- 기간- 일정, 카테고리- 장소, 지역
-- 일정
SELECT
    p1.id,
    c.title,
    p2.photo
FROM
    plan p1,
    content c,
    photo p2
WHERE
    p1.id = c.id
    AND c.id = p2.content_id
    AND c.title LIKE ?
    AND MONTH (p.start_date) <= ?
    AND MONTH (p.end_date) >= ?
    AND s.loc_category = ?;

-- 장소
SELECT
    s.id,
    c.title,
    s.profile_id
FROM
    spot s,
    content c
WHERE
    s.id = c.id
    AND c.title LIKE ?
    AND MONTH (s.start_date) <= ?
    AND MONTH (s.end_date) >= ?
    AND s.category = ?
    AND s.loc_category = ?;