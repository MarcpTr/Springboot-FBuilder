package com.fbuilder.main.service;

import com.fbuilder.main.exception.*;
import com.fbuilder.main.model.User;
import com.fbuilder.main.model.dto.RegisterRequest;
import com.fbuilder.main.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String password) {



        if (userRepository.findByUsername(username).isPresent()) {
            throw new UsernameAlreadyInUse("El nombre de usuario ya está en uso.");
        }
        if ( !Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$")
                .matcher(password)
                .find()) {
            throw new InvalidPasswordFormat("La contraseña no cumple con los requisitos.\nLa contraseña debe tener al menos 6 caracteres, uno minuscula, uno mayuscula y un numero ");
        }
        if ( !Pattern.compile("^(?=.*[A-Za-z])[A-Za-z0-9]+$")
                .matcher(username)
                .find()) {
            throw new InvalidUsernameFormat("El nombre de usuario no es valido.");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return  userRepository.findByUsername(username);
    }

    public Optional<User> findByUserId(long id){
        return  userRepository.findById(id);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not fount"));
        org.springframework.security.core.userdetails.User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        builder.roles("USER");
        return builder.build();
    }
}