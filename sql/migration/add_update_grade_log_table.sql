CREATE TABLE grade_change_log (
      id BIGINT PRIMARY KEY AUTO_INCREMENT,

      student_id BIGINT NOT NULL,

      old_grade DECIMAL(5,2),

      new_grade DECIMAL(5,2),

      reason VARCHAR(255),

      created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

      CONSTRAINT fk_grade_log_student
          FOREIGN KEY(student_id)
              REFERENCES student(id)
);