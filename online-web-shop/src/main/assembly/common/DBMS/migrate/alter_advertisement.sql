ALTER TABLE OWS_ADVERTISEMENTS 
ADD (ADVERTISEMENT_GROUP_ID NUMBER NOT NULL);

ALTER TABLE OWS_ADVERTISEMENTS
ADD CONSTRAINT OWS_ADVERTISEMENTS_GROUP_ID FOREIGN KEY
(
  ADVERTISEMENT_GROUP_ID 
)
REFERENCES OWS_ADVERTISEMENTS_GROUPS
(
  ADVERTISEMENT_GROUP_ID 
)
ENABLE;



ALTER TABLE OWS_ADVERTISEMENTS 
ADD (PRICE FLOAT NOT NULL);