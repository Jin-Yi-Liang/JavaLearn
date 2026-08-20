package org.maxing.learning.jdbc.domain;

import java.math.BigDecimal;

public class GradeChangeLog {
    private static Long num=1L;
    private Long id;
    private Long student_id;
    private BigDecimal old_grade;
    private BigDecimal new_grade;
    private String reason;

    public GradeChangeLog(){}

    public GradeChangeLog(Long student_id, BigDecimal old_grade, BigDecimal new_grade, String reason) {
        this.id = num++;
        this.student_id = student_id;
        this.old_grade = old_grade;
        this.new_grade = new_grade;
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public BigDecimal getOld_grade() {
        return old_grade;
    }

    public void setOld_grade(BigDecimal old_grade) {
        this.old_grade = old_grade;
    }

    public BigDecimal getNew_grade() {
        return new_grade;
    }

    public void setNew_grade(BigDecimal new_grade) {
        this.new_grade = new_grade;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
