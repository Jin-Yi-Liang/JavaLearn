package org.maxing.learning.jdbc.domain;

public class OperationLog {
    private Long id;
    private String username;
    private String operation;

    public OperationLog(){}

    public OperationLog(String username, String operation) {
        this.username = username;
        this.operation = operation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "username='" + username + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
