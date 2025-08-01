package dangelodavide.U2_W3_D5_Friday.service;

import dangelodavide.U2_W3_D5_Friday.entities.Role;
import dangelodavide.U2_W3_D5_Friday.entities.User;
import dangelodavide.U2_W3_D5_Friday.exception.BadRequestException;
import dangelodavide.U2_W3_D5_Friday.exception.UnauthorizedException;
import dangelodavide.U2_W3_D5_Friday.payload.JwtResponse;
import dangelodavide.U2_W3_D5_Friday.payload.LoginRequest;
import dangelodavide.U2_W3_D5_Friday.payload.RegisterRequest;
import dangelodavide.U2_W3_D5_Friday.repositoy.UserRepository;
import dangelodavide.U2_W3_D5_Friday.security.JwtUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, CustomUserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    public void register(RegisterRequest request){
        if (userRepository.existsByEmail(request.email())){
            throw new BadRequestException("email gia in uso");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setEmail(request.email());
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public JwtResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(() -> new UnauthorizedException("credenziali non valide"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UnauthorizedException("credenziali non valide");
        }
        String token = jwtUtils.createToken(user);
        return new JwtResponse(token);
    }
}
