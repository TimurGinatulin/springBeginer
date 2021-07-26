package ru.hw.hw.models.modelsEnum;

public enum UserEnum {
    ADMIN("ADMIN"),
    USER("USER");
    private String role;

    UserEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
