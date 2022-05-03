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
    )),
    ADMINTRAINEE(Sets.newHashSet(
            COURSE_READ,
            STUDENT_READ
    ));

    private final Set<UserPermission> permissions;
}
