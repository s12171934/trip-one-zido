#커뮤니티 게시글 조회
SELECT c.id, c.title, o.member_id writer, c.deadline, c.view_point, c.recruitment, c.status,
  (SELECT COUNT(*)
  FROM owner
  WHERE content_id = id) recruitment_count
FROM community c, owner o
WHERE c.id = o.content_id
  AND o.own = 'writer'
LIMIT 10;

#커뮤니티 게시글 상세 조회
SELECT * FROM community
WHERE id = ?;

SELECT o.member_id, o.own, m.name, m.login_id, m.profile
FROM member m, owner o
WHERE m.id = o.member_id
  AND o.content_id = ?;

#커뮤니티 게시글 등록
INSERT INTO content (type)
VALUE ('community');
  #INSERT시 AI key 값 AI_ID에 저장
  SELECT LAST_INSERT_ID();

INSERT INTO community (id, title, start_date, end_date, loc_category, notice, recruitment, deadline)
VALUE (AI_ID, ?, ?, ?, ?, ?, ?, ?)

INSERT INTO own (type, member_id, content_id)
VALUE ('writer', ?, AI_ID);
