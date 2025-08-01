package dangelodavide.U2_W3_D5_Friday.service;

import dangelodavide.U2_W3_D5_Friday.entities.User;
import dangelodavide.U2_W3_D5_Friday.exception.ResourceNotFoundException;
import dangelodavide.U2_W3_D5_Friday.repositoy.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User con id: " + id + " non trovato"));
    }

    public User getByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User con email: " + email + "  non trovato"));
    }

    public User registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(Long id, User userToUpdate) {
        User existingUser = getById(id);
        existingUser.setUsername(userToUpdate.getUsername());
        existingUser.setEmail(userToUpdate.getEmail());
        if (userToUpdate.getPassword() != null && !userToUpdate.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userToUpdate.getPassword()));
        }
        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }
}
