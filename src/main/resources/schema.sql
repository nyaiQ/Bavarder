CREATE TABLE chat (
    id IDENTITY,
    user CHAR NOT NULL,
    message CHAR NOT NULL,
    time CHAR NOT NULL,
    iine INT
);

CREATE TABLE user (
  id IDENTITY,
  userid CHAR NOT NULL,
  username CHAR NOT NULL,
  password CHAR NOT NULL,
  birth DATE,
  gender CHAR,
  comment CHAR
);
