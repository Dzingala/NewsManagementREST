INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (1,'John',TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (2,'Bill',TO_TIMESTAMP('2015-08-12 06:15:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (3,'Jack',TO_TIMESTAMP('2011-01-03 08:25:00.632030012', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (4,'Mickey',TO_TIMESTAMP('2009-07-02 12:45:00.123400000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (5,'Andrew',TO_TIMESTAMP('2014-12-12 06:41:00.771201230', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (6,'Alfred',TO_TIMESTAMP('2011-01-01 06:27:00.523100000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (7,'Axel',TO_TIMESTAMP('2007-07-02 17:58:00.742000821', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (8,'Colin',TO_TIMESTAMP('2003-09-24 19:14:00.190030050', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (9,'Connor',TO_TIMESTAMP('2016-01-01 22:14:00.709200000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (10,'Benedict',TO_TIMESTAMP('2004-01-13 06:41:00.123003210', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (11,'Boris',TO_TIMESTAMP('2016-11-08 06:29:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (12,'Jakob',TO_TIMESTAMP('2014-07-02 06:31:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (13,'Igor',TO_TIMESTAMP('2014-07-02 07:32:01.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (14,'Heralt',TO_TIMESTAMP('2014-07-02 08:33:12.742123000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (15,'Lanto',TO_TIMESTAMP('2014-07-02 11:34:13.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (16,'Jaydon',TO_TIMESTAMP('2014-07-02 12:35:15.724240000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (17,'Irwin',TO_TIMESTAMP('2014-07-02 14:36:14.286000320', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (18,'Kalem',TO_TIMESTAMP('2014-07-02 13:37:17.162000000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (19,'Marc',TO_TIMESTAMP('2014-07-02 15:38:16.122987650', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO AUTHOR(AUTHOR_ID,AUTHOR_NAME,EXPIRED) VALUES (20,'Scot',TO_TIMESTAMP('2014-07-02 16:39:18.098700000', 'YYYY-MM-DD HH24:MI:SS.FF'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (1,'unbelievable','breaking news','crash of a train and its explanation',
        TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2015-07-02 01:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (2,'amazing','hot news','crash of a car and its explanation',
        TO_TIMESTAMP('2013-07-02 07:15:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 08:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (3,'awesome','fresh news','crash of a bus and its explanation',
        TO_TIMESTAMP('2013-07-02 8:16:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 07:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (4,'awesome2','fresh news2','crash of a bus and its explanation2',
        TO_TIMESTAMP('2013-07-02 8:16:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 07:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (5,'noteworthy','shock!!','super star is coming!!',
        TO_TIMESTAMP('1999-07-02 06:14:00.742043210', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2000-12-11 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (6,'wow','short 1','such a long explanation of such a small fact!',
        TO_TIMESTAMP('2013-07-02 07:15:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (7,'extra','but ordinary','things in our common life',
        TO_TIMESTAMP('2013-07-02 8:16:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (8,'ordinary','but extra','it is something about our lifes too',
        TO_TIMESTAMP('2015-07-02 07:15:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2016-07-02 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (9,'common','days in our country','some ideas about spending time in our country',
        TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2015-07-02 01:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (10,'nothing matters','it is about metallica','would you like to have another concert?',
        TO_TIMESTAMP('2013-07-02 07:15:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 08:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (11,'all is good','let us be positive','positiveness will save the world!',
        TO_TIMESTAMP('2013-07-02 8:16:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 07:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (12,'all is good again','let us be positive again ','positiveness will save the world again!',
        TO_TIMESTAMP('2013-07-02 8:16:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 07:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (13,'well done!','nice goal again!','this footballer is going to rock this stadium',
        TO_TIMESTAMP('1999-07-02 06:14:00.742043210', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2000-12-11 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (14,'would be nice','cooking together','cooking a dinner with a mature professional',
        TO_TIMESTAMP('2013-07-02 07:15:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (15,'seems little strange','while not considered','but it is familiar to everyone of us',
        TO_TIMESTAMP('2013-07-02 8:16:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (16,'super star dancing','in the local club','do not miss such a wonderfull performance of yout favourite pop star!',
        TO_TIMESTAMP('2015-07-02 07:15:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2016-07-02 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (17,'worth visiting','amazing concert is taking a place near you','would be nice to your ears to hear such a soft sounding',
        TO_TIMESTAMP('1999-07-02 06:14:00.742043210', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2000-12-11 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (18,'such a nice place','for having a meal','you will be excited a lot bu this picturesque landscape',
        TO_TIMESTAMP('2013-07-02 07:15:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (19,'you are welcome','here in our caffee','enjoy the taste with us alltogether',
        TO_TIMESTAMP('2013-07-02 8:16:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2014-07-02 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS(NEWS_ID,TITLE,SHORT_TEXT,FULL_TEXT,CREATION_DATE,MODIFICATION_DATE)
VALUES (20,'just a title','for a piece of news','with a long-long-long-long full text conterning news itself',
        TO_TIMESTAMP('2015-07-02 07:15:00.732130000', 'YYYY-MM-DD HH24:MI:SS.FF'),TO_DATE('2016-07-02 06:14:00', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(1,3);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(2,3);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(3,3);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(4,2);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(5,4);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(6,6);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(7,7);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(8,11);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(9,12);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(10,13);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(11,17);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(12,19);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(13,3);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(14,5);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(15,14);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(16,13);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(17,11);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(18,9);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(19,10);
INSERT INTO NEWS_AUTHOR(NEWS_ID,AUTHOR_ID) VALUES(20,4);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (1,'comment text',TO_TIMESTAMP('2014-07-02 06:14:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),1);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (2,'amazing!',TO_TIMESTAMP('2015-08-12 06:15:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),2);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (3,'wow!',TO_TIMESTAMP('2011-01-03 08:25:00.632030012', 'YYYY-MM-DD HH24:MI:SS.FF'),17);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (4,'heyyy!',TO_TIMESTAMP('2009-07-02 12:45:00.123400000', 'YYYY-MM-DD HH24:MI:SS.FF'),15);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (5,'heyaaa, nice!',TO_TIMESTAMP('2014-12-12 06:41:00.771201230', 'YYYY-MM-DD HH24:MI:SS.FF'),14);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (6,'baaaad',TO_TIMESTAMP('2011-01-01 06:27:00.523100000', 'YYYY-MM-DD HH24:MI:SS.FF'),8);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (7,'boooriing',TO_TIMESTAMP('2007-07-02 17:58:00.742000821', 'YYYY-MM-DD HH24:MI:SS.FF'),9);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (8,'i am not quite sure but it is ok',TO_TIMESTAMP('2003-09-24 19:14:00.190030050', 'YYYY-MM-DD HH24:MI:SS.FF'),13);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (9,'well done',TO_TIMESTAMP('2016-01-01 22:14:00.709200000', 'YYYY-MM-DD HH24:MI:SS.FF'),11);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (10,'would be nice',TO_TIMESTAMP('2004-01-13 06:41:00.123003210', 'YYYY-MM-DD HH24:MI:SS.FF'),5);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (11,'great',TO_TIMESTAMP('2016-11-08 06:29:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),4);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (12,'huh, it rocks!',TO_TIMESTAMP('2014-07-02 06:31:00.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),20);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (13,'bad idea imho',TO_TIMESTAMP('2014-07-02 07:32:01.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),19);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (14,'not so bad',TO_TIMESTAMP('2014-07-02 08:33:12.742123000', 'YYYY-MM-DD HH24:MI:SS.FF'),19);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (15,'bullshit.',TO_TIMESTAMP('2014-07-02 11:34:13.742000000', 'YYYY-MM-DD HH24:MI:SS.FF'),18);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (16,'exciting note',TO_TIMESTAMP('2014-07-02 12:35:15.724240000', 'YYYY-MM-DD HH24:MI:SS.FF'),7);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (17,'noteworthy words',TO_TIMESTAMP('2014-07-02 14:36:14.286000320', 'YYYY-MM-DD HH24:MI:SS.FF'),3);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (18,'enjoyable piece of information',TO_TIMESTAMP('2014-07-02 13:37:17.162000000', 'YYYY-MM-DD HH24:MI:SS.FF'),9);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (19,'i love this one',TO_TIMESTAMP('2014-07-02 15:38:16.122987650', 'YYYY-MM-DD HH24:MI:SS.FF'),11);
INSERT INTO COMMENTS(COMMENT_ID,COMMENT_TEXT,CREATION_DATE,NEWS_ID) VALUES (20,'i like the way it discribed',TO_TIMESTAMP('2014-07-02 16:39:18.098700000', 'YYYY-MM-DD HH24:MI:SS.FF'),12);
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (1,'cool');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (2,'awesome');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (3,'amazing');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (4,'unbelievable');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (5,'wow');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (6,'such tag');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (7,'very seventh');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (8,'belter');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (9,'classy');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (10,'bluffy');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (11,'excellent');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (12,'swag');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (13,'great');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (14,'picturesque');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (15,'zingy');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (16,'tremendous');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (17,'incredible');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (18,'fabulous');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (19,'improbable');
INSERT INTO TAG(TAG_ID,TAG_NAME) VALUES (20,'startling');
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(1,3);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(2,2);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(3,3);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(4,2);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(5,4);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(6,6);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(7,7);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(8,11);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(9,12);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(10,13);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(11,17);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(12,19);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(13,3);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(14,5);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(15,17);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(16,13);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(17,11);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(18,9);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(19,10);
INSERT INTO NEWS_TAG(TAG_ID,NEWS_ID) VALUES(20,4);
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (1,'author');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (2,'not author');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (3,'ROLE_ADMIN');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (4,'not admin');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (5,'user');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (6,'not user');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (7,'redactor');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (8,'not redactor');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (9,'director');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (10,'not director');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (11,'general director');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (12,'not general director');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (13,'manager');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (14,'not manager');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (15,'worker');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (16,'not worker');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (17,'journalist');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (18,'not journalist');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (19,'writer');
INSERT INTO ROLES(ROLE_ID,ROLE_NAME) VALUES (20,'not writer');
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (1,'Scot','login1','pass1',1);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (2,'Marc','login2','pass2',2);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (3,'Kalem','login3','pass3',3);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (4,'Irwin','login4','pass4',4);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (5,'Jaydon','login5','pass5',5);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (6,'Lanto','login6','pass6',8);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (7,'Heralt','login7','pass7',5);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (8,'Igor','login8','pass8',2);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (9,'Jakob','login9','pass9',1);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (10,'Boris','login10','pass10',12);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (11,'Benedict','login11','pass11',13);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (12,'Connor','login12','pass12',14);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (13,'Colin','login13','pass13',17);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (14,'Axel','login14','pass14',18);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (15,'Alfred','login15','pass15',19);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (16,'Andrew','login16','pass16',20);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (17,'Mickey','login17','pass17',11);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (18,'Jack','login18','pass18',14);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (19,'Bill','login19','pass19',4);
INSERT INTO USERS(USER_ID,USER_NAME,LOGIN,PASSWORD,ROLE_ID) VALUES (20,'John','login20','pass20',10);