--회원가입login_id
SELECT
  COUNT(id)
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
  id
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
SELECT
  id
FROM
  member
WHERE
  login_id = ?
  AND name = ?
  AND email = ?;

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