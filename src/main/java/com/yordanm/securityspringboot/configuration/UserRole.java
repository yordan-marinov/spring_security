package com.yordanm.securityspringboot.configuration;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import static com.yordanm.securityspringboot.configuration.UserPermission.*;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(
            COURSE_READ,
            COURSE_WRITE,
            STUDENT_READ,
            STUDENT_WRITE
    ));

    private final Set<UserPermission> permissions;
}
