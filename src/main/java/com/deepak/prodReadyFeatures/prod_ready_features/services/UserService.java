package com.deepak.prodReadyFeatures.prod_ready_features.services;

import com.deepak.prodReadyFeatures.prod_ready_features.dto.LoginDto;
import com.deepak.prodReadyFeatures.prod_ready_features.dto.SignupDto;
import com.deepak.prodReadyFeatures.prod_ready_features.dto.UserDto;
import com.deepak.prodReadyFeatures.prod_ready_features.entities.User;
import com.deepak.prodReadyFeatures.prod_ready_features.exceptions.ResourceNotFoundException;
import com.deepak.prodReadyFeatures.prod_ready_features.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()-> new BadCredentialsException("User with email"+username+" not found"));
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(()-> new BadCredentialsException("User with id"+userId+" not found"));
    }

    public UserDto signUp(SignupDto signupDto) {
        Optional<User> user= userRepository.findByEmail(signupDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with email already exists"+ signupDto.getEmail());
        }

        User toBeCreatedUser= modelMapper.map(signupDto,User.class);

        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));
        User savedUser= userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser,UserDto.class);
    }

}
