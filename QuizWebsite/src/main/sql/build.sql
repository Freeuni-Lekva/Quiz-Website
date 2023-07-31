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

-- Creates the quizzes table. Contains information about quizzes:
-- name, description, duration and other important details.
-- There are quiz_id - primary key and author_id - foreign key that references
-- user_id column of users table
CREATE TABLE IF NOT EXISTS quizzes (
    quiz_id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_name VARCHAR(100),
    quiz_description VARCHAR(5000),
    quiz_duration INT,
    random_questions BOOLEAN,
    multiple_pages BOOLEAN,
    immediate_feedback BOOLEAN,
    author_id int references users(user_id)
);

-- Creates questions table. Contains information about questions:
-- type, text. There are question_id as primary key and quiz_id
-- references quiz_id column of quizzes table
CREATE TABLE IF NOT EXISTS questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    question_type VARCHAR(50),
    question_content VARCHAR(5000),
    quiz_id INT REFERENCES quizzes(quiz_id)
);
