CREATE TABLE `student` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `student_no` varchar(32) NOT NULL,
   `name` varchar(64) NOT NULL,
   `age` int NOT NULL,
   `grade` decimal(5,2) NOT NULL,
   `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `updated_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   UNIQUE KEY `student_no` (`student_no`),
   CONSTRAINT `ck_student_age` CHECK ((`age` between 1 and 150)),
   CONSTRAINT `ck_student_grade` CHECK ((`grade` between 0 and 100))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci