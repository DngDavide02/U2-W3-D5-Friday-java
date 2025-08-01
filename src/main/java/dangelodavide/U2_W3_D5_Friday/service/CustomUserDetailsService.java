package dangelodavide.U2_W3_D5_Friday.service;

import dangelodavide.U2_W3_D5_Friday.entities.User;
import dangelodavide.U2_W3_D5_Friday.repositoy.UserRepository;
import dangelodavide.U2_W3_D5_Friday.security.CustomUserDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("utente con email: " + email + " non trovato"));
        return new CustomUserDetail(user);
    }

    public CustomUserDetail loadUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("utente con id: " + id + " non trovato"));
        return new CustomUserDetail(user);
    }


}
