CREATE TABLE OWS_LIKES 
(
  LIKES_ID NUMBER NOT NULL 
, LIKES_COUNT NUMBER NOT NULL 
, REFID NUMBER NOT NULL 
, LIKE_TYPE VARCHAR2(3) 
, CONSTRAINT OWS_LIKES_PK PRIMARY KEY 
  (
    LIKES_ID 
  )
  ENABLE 
);

COMMENT ON COLUMN OWS_LIKES.REFID IS 'refid - depends of the type for which is this like count ( advertisements, pictures,comments ) ';

COMMENT ON COLUMN OWS_LIKES.LIKE_TYPE IS 'ADV - advertisement
IMG - image
CMT - comment
PRF - profil';
