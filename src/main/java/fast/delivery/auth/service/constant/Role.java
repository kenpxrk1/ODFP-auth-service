package fast.delivery.auth.service.constant;

public enum Role {
    ROLE_USER;

    public static Role getDefaultRole() {
        return Role.ROLE_USER;
    }
}
