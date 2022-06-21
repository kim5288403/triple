# triple

###evnet (포인트 부여)  

postman url : http://localhost:8081//triple/event

request type : json

request : {
  "type": "REVIEW",
  "action": "ADD",
  "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772c",
  "content": "좋아요!",
  "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8","afb0cef2-851d-4a50-bb07-9cc15cbdc332"],
  "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
  "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}

###point (포인트 조회)

postman url : http://localhost:8081//triple/point

request type : json

request : {
"userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745"
}


# mysql table

###point 테이블 create 코드

''' mysql

  CREATE TABLE `point` (
    `ID` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `userId` TEXT NULL DEFAULT NULL COLLATE 'utf8_general_ci',
    `placeId` TEXT NULL DEFAULT NULL COLLATE 'utf8_general_ci',
    `reviewId` TEXT NULL DEFAULT NULL COLLATE 'utf8_general_ci',
    `point` INT(11) NULL DEFAULT NULL,
    `created_at` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE
  )
  COLLATE='armscii8_bin'
  ENGINE=InnoDB
  AUTO_INCREMENT=33
  ;

'''

###point_history 테이블 create 코드

''' mysql

  CREATE TABLE `point_history` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `pointId` BIGINT(20) NULL DEFAULT NULL,
    `content` VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8_general_ci',
    `created_at` DATETIME NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
  )
  COLLATE='armscii8_bin'
  ENGINE=InnoDB
  AUTO_INCREMENT=21
  ;

'''


