CREATE TABLE OWS_ADVERTISEMENTS_GROUPS 
(
  ADVERTISEMENT_ID NUMBER NOT NULL 
, GROUP_NAME VARCHAR2(25) NOT NULL 
, GROUP_INFO VARCHAR2(200) 
, CONSTRAINT OWS_ADVERTISEMENTS_GROUPS_PK PRIMARY KEY 
  (
    ADVERTISEMENT_ID 
  )
  ENABLE 
);

ALTER TABLE OWS_ADVERTISEMENTS_GROUPS
ADD CONSTRAINT OWS_ADVERTISEMENTS_GROUPS_UK1 UNIQUE 
(
  GROUP_NAME 
)
ENABLE;