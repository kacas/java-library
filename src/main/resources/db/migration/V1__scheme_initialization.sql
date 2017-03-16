CREATE TABLE IF NOT EXISTS `books` (
  `book_id`       BINARY(16) NOT NULL,
  `title`         VARCHAR(255),
  `description`   LONGTEXT,
  `author`        VARCHAR(255),
  `isbn`          VARCHAR(17),
  PRIMARY KEY (`book_id`)
);