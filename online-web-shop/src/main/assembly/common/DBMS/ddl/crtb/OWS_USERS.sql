CREATE TABLE OWS_USERS 
(
  USER_ID NUMBER NOT NULL 
, USERNAME VARCHAR2(25 BYTE) NOT NULL 
, PASSWORD VARCHAR2(25 BYTE) NOT NULL 
, COUNTRY VARCHAR2(30 BYTE) NOT NULL 
, CITY VARCHAR2(30 BYTE) 
, TELEPHONE VARCHAR2(20 BYTE) 
, MAIL VARCHAR2(30 BYTE) 
, PROFILE_OPENED_COUNT NUMBER DEFAULT 0 
, CURRENT_ADS NUMBER DEFAULT 0 
, USER_STATUS NUMBER DEFAULT 1 NOT NULL 
, BANNED_IP VARCHAR2(20 BYTE) DEFAULT NULL 
, CONSTRAINT OWS_USERS_PK PRIMARY KEY 
  (
    USER_ID 
  )
  ENABLE 
) 
LOGGING 
TABLESPACE "USERS" 
PCTFREE 10 
INITRANS 1 
STORAGE 
( 
  BUFFER_POOL DEFAULT 
);

ALTER TABLE OWS_USERS
ADD CONSTRAINT OWS_USERS_UK1 UNIQUE 
(
  USERNAME 
, PASSWORD 
, TELEPHONE 
, MAIL 
)
ENABLE;

COMMENT ON COLUMN OWS_USERS.CURRENT_ADS IS 'current adds count';

COMMENT ON COLUMN OWS_USERS.USER_STATUS IS '1 - simple user
2 - admin
3 - banned
4 - deleted user
5 - banned';

COMMENT ON COLUMN OWS_USERS.BANNED_IP IS 'banned by IP address';
