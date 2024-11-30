package com.udemy.helpdesk.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Profile {
    ADMIN(0, "ROLE_ADMIN"),
    CLIENT(1, "ROLE_CLIENT"),
    TECHNICIAN(2, "ROLE_TECHNICIAN");

    private Integer code;
    private String description;

    public static Profile toEnum(Integer code, String description) {
        if (code == null) {
            return null;
        }
        for (Profile x: Profile.values()) {
            if (code.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid Profile");
    }
}
