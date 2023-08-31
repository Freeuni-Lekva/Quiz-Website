CREATE SCHEMA IF NOT EXISTS QuizWebsite;
USE QuizWebsite;

-- Create the users table. Table has 5 columns, user_id is primary
-- and auto incremented, username should be unique for each user
CREATE TABLE IF NOT EXISTS users
(
    user_id   INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(100) UNIQUE,
    password  VARCHAR(100),
    user_type VARCHAR(100),
    salt      VARCHAR(100)
    );

ALTER TABLE users
    ADD CONSTRAINT unique_username UNIQUE (username);

DROP TABLE IF EXISTS friends;

-- Create Friends Table. Table has 2 columns - first one stores user's id
-- second one - another user's id which is friend of this user
CREATE TABLE IF NOT EXISTS friends
(
    user_id  INT,
    friend_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES users(user_id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS messages;

CREATE TABLE IF NOT EXISTS messages
(
    id            int primary key auto_increment,
    from_username VARCHAR(100),
    to_username   VARCHAR(100),
    message       VARCHAR(1000) not null,
    sent_date     timestamp     not null,
    foreign key (from_username) references users (username) on delete cascade,
    foreign key (to_username) references users (username) on delete cascade
);

DROP TABLE IF EXISTS challenges;

CREATE TABLE IF NOT EXISTS challenges
(
    id            int primary key auto_increment,
    from_username VARCHAR(100),
    to_username   VARCHAR(100),
    quiz_id       VARCHAR(1000) not null,
    foreign key (from_username) references users (username) on delete cascade,
    foreign key (to_username) references users (username) on delete cascade
);

DROP TABLE IF EXISTS friend_requests;

CREATE TABLE IF NOT EXISTS friend_requests
(
    id            int primary key auto_increment,
    from_username VARCHAR(100),
    to_username   VARCHAR(100),
    foreign key (from_username) references users (username) on delete cascade,
    foreign key (to_username) references users (username) on delete cascade
);

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

DROP TABLE IF EXISTS questions;


-- Creates the questions table. Contains information about questions.
CREATE TABLE IF NOT EXISTS questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    question_content VARCHAR(5000),
    question_type VARCHAR(100),
    picture_url VARCHAR(200),
    quiz_id INT REFERENCES quizzes (quiz_id) ON DELETE CASCADE,
    answer VARCHAR(100)
);

-- Creates the answers table
CREATE TABLE IF NOT EXISTS answers (
    answer_id INT AUTO_INCREMENT PRIMARY KEY,
    answer VARCHAR(100),
    question_id INT REFERENCES questions (question_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS history (
    history_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    quiz_id INT,
    grade DECIMAL(5, 2),
    duration TIME,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id) ON DELETE CASCADE
);

CREATE TABLE announcements (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP,
    text TEXT
);


