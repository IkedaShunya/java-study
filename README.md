| students | CREATE TABLE `students` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `name_ruby` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `email_address` varchar(100) DEFAULT NULL,
  `area` varchar(100) DEFAULT NULL,
  `age` int DEFAULT NULL,
  `gender` varchar(100) DEFAULT NULL,
  `remark` varchar(30) DEFAULT NULL,
  `delete_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0:non_delete 1:delete',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ID` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 |

| students_courses | CREATE TABLE `students_courses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_ID` int DEFAULT NULL,
  `course_name` varchar(30) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_expected_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `course_ID` (`id`),
  KEY `student_ID` (`student_ID`),
  CONSTRAINT `students_courses_ibfk_1` FOREIGN KEY (`student_ID`) REFERENCES `students` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3 |
