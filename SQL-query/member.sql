--회원가입
--아이디 중복확인
SELECT
  COUNT(*)
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
  name,
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

--보안질문 들어간 후
SELECT
  question,
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
SELECT
  id
FROM
  member
WHERE
  id = ?
  AND password = ?;

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

--프로필사진 변경
UPDATE member
SET
  profile = ?
WHERE
  id = ?;

--비밀번호 변경
SELECT
  id
FROM
  member
WHERE
  id = ?
  AND password = ?;

UPDATE member
SET
  password = ?
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