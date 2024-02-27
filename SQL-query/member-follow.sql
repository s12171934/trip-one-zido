--회원가입
--보안질문 응답
SELECT
  *
FROM
  security_questions;

--아이디 중복확인
SELECT
  login_id,
  password
FROM
  member
WHERE
  login_id = ?;

INSERT INTO
  member (
    name,
    login_id,
    password,
    question,
    answer,
    email,
    phone_number,
    address,
    birth,
    gender
  ) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);

--로그인
SELECT
  id,
  login_id,
  profile
FROM
  member
WHERE
  login_id = ?
  AND password = ?;

--아이디 찾기
SELECT
  login_id
FROM
  member
WHERE
  name = ?
  AND email = ?;

--비밀번호 찾기 / 재등록
--보안질문 들어가기 전
SELECT
  id
FROM
  member
WHERE
  login_id = ?
  AND name = ?
  AND email = ?;

--보안질문 들어갈 때
SELECT
  q.question
FROM
  member m,
  security_questions q
WHERE
  m.question = q.id
  AND m.id = ?;

--보안질문 응답할 때
SELECT
  answer
FROM
  member
WHERE
  id = ?;

UPDATE member
SET
  password = ?
WHERE
  id = ?;

--정보변경
UPDATE member
SET
  name = ?,
  question = ?,
  answer = ?,
  email = ?,
  phone_number = ?,
  address = ?,
  gender = ?
WHERE
  id = ?;

--회원탈퇴
SELECT
  id
FROM
  member
WHERE
  id = ?,
  password = ?;

DELETE FROM member
WHERE
  id = ?;

--프로필사진 변경
UPDATE member
SET
  profile = ?
WHERE
  id = ?;

--비밀번호 변경
UPDATE member
SET
  password = ?
WHERE
  id = ?;

--sns 연동을 통한 회원가입


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