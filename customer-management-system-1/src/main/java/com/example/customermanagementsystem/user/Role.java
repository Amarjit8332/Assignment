package com.example.customermanagementsystem.user;

import java.util.Arrays;
import java.util.List;

public enum Role {

	CUSTOMER(Arrays.asList(Permission.READ_ALL_CUSTOMERS)),

    ADMIN(Arrays.asList(Permission.READ_ALL_CUSTOMERS, Permission.SAVE_ONE_CUSTOMER));

    private List<Permission> permissions;

    Role(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
