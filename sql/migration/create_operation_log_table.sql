CREATE TABLE `operation_log` (
     `id`         bigint       NOT NULL AUTO_INCREMENT,
     `username`   varchar(64)  NOT NULL,
     `operation`  varchar(255) NOT NULL,
     `created_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP,
     PRIMARY KEY (`id`),
     KEY `idx_operation_log_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;