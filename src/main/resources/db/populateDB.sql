DELETE FROM news;
DELETE FROM news_type;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO news_type (name_type, color_type)
VALUES ('General', 'Green'),
       ('IT', 'Red');

INSERT INTO news (name, description_short, description_full, type_news_id) VALUES
  ('Global warming', 'Changing of the climate', 'Due to global warming, Spain and Portugal are experiencing the worst drought in 1200 years.', 100000),
  ('Intel', 'Intel Meteor Lake processors', 'Intel Meteor Lake processors will have a third type of cores', 100001);



