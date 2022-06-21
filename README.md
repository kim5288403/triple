# triple

###evnet (포인트 부여)  
postman url : http://localhost:8081//triple/event

###point (포인트 조회)
postman url : http://localhost:8081//triple/point

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


