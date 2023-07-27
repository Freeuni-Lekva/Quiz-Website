CREATE SCHEMA IF NOT EXISTS QuizWebsite;
USE QuizWebsite;

DROP TABLE IF EXISTS friends;

-- Create Friends Table. Table has 2 columns - first one stores user's id
-- second one - another user's id which is friend of this user
CREATE TABLE friends
(
    user_id  INT,
    friend_id INT
);

-- Create the users table. Table has 5 columns, user_id is primary
-- and auto incremented, username should be unique for each user
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    user_type VARCHAR(100),
    salt VARCHAR(100)
);

ALTER TABLE users
ADD CONSTRAINT unique_username UNIQUE (username);
