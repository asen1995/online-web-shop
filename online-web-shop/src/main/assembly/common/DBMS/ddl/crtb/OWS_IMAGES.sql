CREATE TABLE OWS_IMAGES 
(
  IMAGE_ID NUMBER NOT NULL 
, IMAGE_INFO VARCHAR2(200) 
, IMAGE_TYPE VARCHAR2(1) NOT NULL 
, REFID NUMBER NOT NULL 
, IMAGE_CONTENT BLOB NOT NULL 
, DATE_UPLOAD DATE DEFAULT sysdate 
, CONSTRAINT OWS_IMAGES_PK PRIMARY KEY 
  (
    IMAGE_ID 
  )
  ENABLE 
);

COMMENT ON COLUMN OWS_IMAGES.IMAGE_TYPE IS 'P = profile picture
A = advertisement';

COMMENT ON COLUMN OWS_IMAGES.REFID IS 'refId - can be userId or advertisementID base on which type of picture is uploaded';