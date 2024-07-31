-- Insert some test users

INSERT INTO academy_chat.`user`
(id, first_name, last_name, email, user_name, password, status)
VALUES(2, 'John', 'Smith', 'john.smith@gmail.com', 'johnSmith', 'johnSmith#test1', 'OFFLINE');
INSERT INTO academy_chat.`user`
(id, first_name, last_name, email, user_name, password, status)
VALUES(3, 'Christina', 'Sasheva', 'ch.ablanska@gmail.com', 'ChrisiA', 'password', 'ONLINE');
INSERT INTO academy_chat.`user`
(id, first_name, last_name, email, user_name, password, status)
VALUES(8, 'Kate', 'Winslett', 'teyt.ablanska@gmail.com', 'Kate', 'password', 'OFFLINE');
INSERT INTO academy_chat.`user`
(id, first_name, last_name, email, user_name, password, status)
VALUES(9, 'Simon', 'Wollis', 'simon.test@gmail.com', 'Simo', 'password', 'ONLINE');
INSERT INTO academy_chat.`user`
(id, first_name, last_name, email, user_name, password, status)
VALUES(10, 'Emili', 'Drake', 'em.dr@gmail.com', 'Emko', 'testPass', 'ONLINE');

-- Insert some sample messages

INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(10, 'Hello test', 10, 9, 'SENT', '2024-07-24 19:50:13');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(11, 'Hello test', 10, 8, 'SENT', '2024-07-25 17:01:35');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(12, 'Hello, Kate!', 3, 8, 'SENT', '2024-07-29 19:04:32');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(13, 'Hello, Kate!', 3, 8, 'SENT', '2024-07-29 19:04:32');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(14, 'Hello, Kate!', 3, 8, 'SENT', '2024-07-26 08:50:32');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(15, 'Hello, Simo!', 3, 9, 'SENT', '2024-07-27 17:45:18');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(16, 'Hello, test Simo!', 3, 9, 'SENT', '2024-07-27 17:45:18');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(17, 'Hello, test Simo!', 3, 9, 'SENT', '2024-07-27 17:45:18');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(18, 'Hello, again TEST!', 3, 9, 'SENT', '2024-07-27 17:45:18');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(19, 'Hello, again TEST!', 3, 9, 'SENT', '2024-07-27 17:45:52');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(20, 'Hello, DaVinci', 3, 12, 'PENDING', '2024-07-27 18:08:55');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(21, 'Hello, DaVinci', 3, 12, 'PENDING', '2024-07-27 18:08:55');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(22, 'Hello, Kate', 3, 8, 'SENT', '2024-07-29 19:05:04');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(23, 'Hello, Kate', 3, 8, 'SENT', '2024-07-29 19:07:30');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(24, 'Hello, Kate', 3, 8, 'SENT', '2024-07-29 19:08:01');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(25, 'Hello, Kate', 3, 8, 'SENT', '2024-07-31 16:31:23');
INSERT INTO academy_chat.message
(id, content, sender_id, receiver_id, status, time_stamp)
VALUES(26, 'Hello, logout Kate', 3, 8, 'SENT', '2024-07-31 16:32:26');