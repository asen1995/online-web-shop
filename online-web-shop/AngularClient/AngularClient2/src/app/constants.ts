export const constants = {
 
    REGISTRATION_SUCCESS: 'SUCCESS',


    REGULAR_EXPRESSIONS :  {
      usernameRegex:  /^[a-zA-Z0-9\-]{5,25}$/,
      passwordRegex: /^[a-zA-Z0-9\-_]{6,40}$/,
      cityRegex : /^[a-zA-Z\-_]{2,30}$/,
      telephoneRegex : /^[+3590-9]{10,14}$/,
      mailRegex :/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
    },

    ERROR_MESSAGES : {
      INVALID_USERNAME_REGISTRATION : " The user name has to have at least 5 characters and max 25 ",
      INVALID_PASSWORD_REGISTRATION : " The password has to have at least 6 characters and max 40  ",
      INVALID_CITY_REGISTRATION : " The city has to have at least 2 characters and max 30  ",
      INVALID_TELEPHONE_REGISTRATION : " The telephone has to have at least 10-14 digits",
      INVALID_MAIL_REGISTRATION : " The mail is invalid!",
      INVALID_IMAGE_REGISTRATION : " Please select image! "
      
    }
  };
  