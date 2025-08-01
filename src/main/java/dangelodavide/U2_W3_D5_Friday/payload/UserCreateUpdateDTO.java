package dangelodavide.U2_W3_D5_Friday.payload;

import dangelodavide.U2_W3_D5_Friday.entities.User;

public record UserCreateUpdateDTO(
        String username,
        String email,
        String password
) {
    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
