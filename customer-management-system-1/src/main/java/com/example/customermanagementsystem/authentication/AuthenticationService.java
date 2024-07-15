package com.example.customermanagementsystem.authentication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.customermanagementsystem.config.JwtService;
import com.example.customermanagementsystem.user.User;
import com.example.customermanagementsystem.user.UserRepository;

@Service
public class AuthenticationService {

	
	public AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtService jwtService;
	
	private final PasswordEncoder passwordEncoder;

	   
	public AuthenticationService(PasswordEncoder passwordEncoder) {
	        
		this.passwordEncoder = passwordEncoder;
	    
	}


	    public AuthenticationResponse register(User request){
	        var user = new User();
	        user.setName(request.getName());
	        user.setUsername(request.getUsername());
	        user.setPassword(passwordEncoder.encode(request.getPassword()));
	        user.setRole(request.getRole());
	        userRepository.save(user);
	        String token = jwtService.generateToken(user, generateExtraClaims(user));
	        return  new AuthenticationResponse(token);
	    }
	
	public AuthenticationResponse login(AuthenticationRequest authenticationRequest){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
        );
        authenticationManager.authenticate(authToken);
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).get();
        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        return new AuthenticationResponse(jwt);
    }




    private Map<String, Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        return extraClaims;
    }
}
