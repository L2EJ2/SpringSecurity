package com.ohgiraffers.session.auth.model;

public enum UserRole {

    USER("USER"),
    ADMIN("ADMIN");

    // 필드
    private String role;

    // 매개변수 있는 생성자
    UserRole(String role) {
        this.role = role;
    }

    // Getter(Setter 없음!!~!~!)
    public String getRole() {
        return role;
    }

    // toString()
    @Override
    public String toString() {
        return "UserRole {" +
                "role='" + role + '\'' +
                '}';
    }
}
