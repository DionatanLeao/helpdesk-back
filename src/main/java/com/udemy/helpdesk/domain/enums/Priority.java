package com.udemy.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Priority {
    LOW(0, "LOW"),
    MEDIUM(1, "MEDIUM"),
    HIGH(2, "HIGH");

    private Integer code;
    private String description;

    public static Priority toEnum(Integer code) {
        if (code == null) {
            return null;
        }
        for (Priority x: Priority.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid Priority");
    }
}
