INSERT INTO
  people (username, name)
VALUES
  ('duckdon', 'Donald Duck'),
  ('mousemic', 'Mickey Mouse'),
  ('mcducscr', 'Scrooge McDuck');

INSERT INTO
  skills (tag, name)
VALUES
  ('java', 'Java'),
  ('project-management', 'Project Management');

INSERT INTO
  organizations (name)
VALUES
  ('Acme Corp.'),
  ('Globex'),
  ('Soylent');

INSERT INTO
  projects (source_id, name)
VALUES
  ((SELECT id FROM people WHERE username = 'mcducscr'), 'Globex Intranet'),
  ((SELECT id FROM people WHERE username = 'mcducscr'), 'Acme Corp. Intranet'),
  ((SELECT id FROM people WHERE username = 'mcducscr'), 'Soylent Coffee Website')
;

INSERT INTO
  personal_abilities (skill_id, target_id, level)
VALUES
  ((SELECT id FROM skills WHERE tag = 'java'),
   (SELECT id FROM people WHERE username = 'duckdon'), 1),
  ((SELECT id FROM skills WHERE tag = 'project-management'),
   (SELECT id FROM people WHERE username = 'duckdon'), 1)
;
