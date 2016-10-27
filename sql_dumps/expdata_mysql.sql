--
-- ������ ������������ Devart dbForge Studio for MySQL, ������ 7.1.31.0
-- �������� �������� ��������: http://www.devart.com/ru/dbforge/mysql/studio
-- ���� �������: 10/23/2016 8:37:28 PM
-- ������ �������: 5.6.15
-- ������ �������: 4.1
--


SET NAMES 'utf8';

INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(1, 'John', '2014-07-02 06:14:01');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(2, 'Bill', '2015-08-12 06:15:01');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(3, 'Jack', '2011-01-03 08:25:01');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(4, 'Mickey', '2009-07-02 12:45:00');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(5, 'Andrew', '2014-12-12 06:41:01');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(6, 'Alfred', '2011-01-01 06:27:01');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(7, 'Axel', '2007-07-02 17:58:01');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(8, 'Colin', '2003-09-24 19:14:00');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(9, 'Connor', '2016-01-01 22:14:01');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(10, 'Benedict', '2004-01-13 06:41:00');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(11, 'Boris', '2016-11-08 06:29:01');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(12, 'Jakob', '2014-07-02 06:31:01');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(13, 'Igor', '2014-07-02 07:32:02');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(14, 'Heralt', '2014-07-02 08:33:13');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(15, 'Lanto', '2014-07-02 11:34:14');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(16, 'Jaydon', '2014-07-02 12:35:16');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(17, 'Irwin', '2014-07-02 14:36:14');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(18, 'Kalem', '2014-07-02 13:37:17');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(19, 'Marc', '2014-07-02 15:38:16');
INSERT INTO dzinhala.author(AUTHOR_ID, AUTHOR_NAME, EXPIRED) VALUES
(20, 'Scot', '2014-07-02 16:39:18');

INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(1, 'unbelievable', 'breaking news', 'crash of a train and its explanation', '2014-07-02 06:14:01', '2015-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(2, 'amazing', 'hot news', 'crash of a car and its explanation', '2013-07-02 07:15:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(3, 'awesome', 'fresh news', 'crash of a bus and its explanation', '2013-07-02 08:16:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(4, 'awesome2', 'fresh news2', 'crash of a bus and its explanation2', '2013-07-02 08:16:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(5, 'noteworthy', 'shock!!', 'super star is coming!!', '1999-07-02 06:14:01', '2000-12-11');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(6, 'wow', 'short 1', 'such a long explanation of such a small fact!', '2013-07-02 07:15:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(7, 'extra', 'but ordinary', 'things in our common life', '2013-07-02 08:16:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(8, 'ordinary', 'but extra', 'it is something about our lifes too', '2015-07-02 07:15:01', '2016-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(9, 'common', 'days in our country', 'some ideas about spending time in our country', '2014-07-02 06:14:01', '2015-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(10, 'nothing matters', 'it is about metallica', 'would you like to have another concert?', '2013-07-02 07:15:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(11, 'all is good', 'let us be positive', 'positiveness will save the world!', '2013-07-02 08:16:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(12, 'all is good again', 'let us be positive again ', 'positiveness will save the world again!', '2013-07-02 08:16:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(13, 'well done!', 'nice goal again!', 'this footballer is going to rock this stadium', '1999-07-02 06:14:01', '2000-12-11');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(14, 'would be nice', 'cooking together', 'cooking a dinner with a mature professional', '2013-07-02 07:15:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(15, 'seems little strange', 'while not considered', 'but it is familiar to everyone of us', '2013-07-02 08:16:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(16, 'super star dancing', 'in the local club', 'do not miss such a wonderfull performance of yout favourite pop star!', '2015-07-02 07:15:01', '2016-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(17, 'worth visiting', 'amazing concert is taking a place near you', 'would be nice to your ears to hear such a soft sounding', '1999-07-02 06:14:01', '2000-12-11');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(18, 'such a nice place', 'for having a meal', 'you will be excited a lot bu this picturesque landscape', '2013-07-02 07:15:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(19, 'you are welcome', 'here in our caffee', 'enjoy the taste with us alltogether', '2013-07-02 08:16:01', '2014-07-02');
INSERT INTO dzinhala.news(NEWS_ID, TITLE, SHORT_TEXT, FULL_TEXT, CREATION_DATE, MODIFICATION_DATE) VALUES
(20, 'just a title', 'for a piece of news', 'with a long-long-long-long full text conterning news itself', '2015-07-02 07:15:01', '2016-07-02');

INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (1, 1, 'comment text', '2014-07-02 06:14:01');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (2, 2, 'amazing!', '2015-08-12 06:15:01');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (3, 17, 'wow!', '2011-01-03 08:25:01');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (4, 15, 'heyyy!', '2009-07-02 12:45:00');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (5, 14, 'heyaaa, nice!', '2014-12-12 06:41:01');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (6, 8, 'baaaad', '2011-01-01 06:27:01');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (7, 9, 'boooriing', '2007-07-02 17:58:01');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (8, 13, 'i am not quite sure but it is ok', '2003-09-24 19:14:00');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (9, 11, 'well done', '2016-01-01 22:14:01');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (10, 5, 'would be nice', '2004-01-13 06:41:00');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (11, 4, 'great', '2016-11-08 06:29:01');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (12, 20, 'huh, it rocks!', '2014-07-02 06:31:01');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (13, 19, 'bad idea imho', '2014-07-02 07:32:02');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (14, 19, 'not so bad', '2014-07-02 08:33:13');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (15, 18, 'bullshit.', '2014-07-02 11:34:14');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (16, 7, 'exciting note', '2014-07-02 12:35:16');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (17, 3, 'noteworthy words', '2014-07-02 14:36:14');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (18, 9, 'enjoyable piece of information', '2014-07-02 13:37:17');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (19, 11, 'i love this one', '2014-07-02 15:38:16');
INSERT INTO dzinhala.comments(COMMENT_ID, NEWS_ID, COMMENT_TEXT, CREATION_DATE) VALUES
  (20, 12, 'i like the way it discribed', '2014-07-02 16:39:18');

INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(1, 3);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(2, 3);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(3, 3);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(4, 2);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(5, 4);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(6, 6);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(7, 7);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(8, 11);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(9, 12);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(10, 13);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(11, 17);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(12, 19);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(13, 3);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(14, 5);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(15, 14);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(16, 13);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(17, 11);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(18, 9);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(19, 10);
INSERT INTO dzinhala.news_author(NEWS_ID, AUTHOR_ID) VALUES
(20, 4);

INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(1, 'author');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(2, 'not author');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(3, 'ROLE_ADMIN');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(4, 'not admin');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(5, 'user');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(6, 'not user');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(7, 'redactor');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(8, 'not redactor');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(9, 'director');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(10, 'not director');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(11, 'general director');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(12, 'not general director');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(13, 'manager');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(14, 'not manager');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(15, 'worker');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(16, 'not worker');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(17, 'journalist');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(18, 'not journalist');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(19, 'writer');
INSERT INTO dzinhala.roles(ROLE_ID, ROLE_NAME) VALUES
(20, 'not writer');

INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(1, 'cool');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(2, 'awesome');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(3, 'amazing');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(4, 'unbelievable');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(5, 'wow');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(6, 'such tag');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(7, 'very seventh');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(8, 'belter');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(9, 'classy');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(10, 'bluffy');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(11, 'excellent');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(12, 'swag');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(13, 'great');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(14, 'picturesque');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(15, 'zingy');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(16, 'tremendous');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(17, 'incredible');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(18, 'fabulous');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(19, 'improbable');
INSERT INTO dzinhala.tag(TAG_ID, TAG_NAME) VALUES
(20, 'startling');

INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (3, 1);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (2, 2);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (3, 3);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (2, 4);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (4, 5);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (6, 6);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (7, 7);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (11, 8);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (12, 9);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (13, 10);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (17, 11);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (19, 12);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (3, 13);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (5, 14);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (17, 15);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (13, 16);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (11, 17);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (9, 18);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (10, 19);
INSERT INTO dzinhala.news_tag(NEWS_ID, TAG_ID) VALUES
  (4, 20);

INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(1, 'Scot', 'login1', 'pass1', 1);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(2, 'Marc', 'login2', 'pass2', 2);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(3, 'Kalem', 'login3', 'pass3', 3);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(4, 'Irwin', 'login4', 'pass4', 4);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(5, 'Jaydon', 'login5', 'pass5', 5);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(6, 'Lanto', 'login6', 'pass6', 8);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(7, 'Heralt', 'login7', 'pass7', 5);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(8, 'Igor', 'login8', 'pass8', 2);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(9, 'Jakob', 'login9', 'pass9', 1);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(10, 'Boris', 'login10', 'pass10', 12);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(11, 'Benedict', 'login11', 'pass11', 13);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(12, 'Connor', 'login12', 'pass12', 14);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(13, 'Colin', 'login13', 'pass13', 17);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(14, 'Axel', 'login14', 'pass14', 18);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(15, 'Alfred', 'login15', 'pass15', 19);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(16, 'Andrew', 'login16', 'pass16', 20);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(17, 'Mickey', 'login17', 'pass17', 11);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(18, 'Jack', 'login18', 'pass18', 14);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(19, 'Bill', 'login19', 'pass19', 4);
INSERT INTO dzinhala.users(USER_ID, USER_NAME, LOGIN, PASSWORD, ROLE_ID) VALUES
(20, 'John', 'login20', 'pass20', 10);