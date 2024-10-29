package com.usco.demo_security.services;

import com.usco.demo_security.Repository.RoleRepository;
import com.usco.demo_security.Repository.UserRepository;
import com.usco.demo_security.models.Role;
import com.usco.demo_security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User crearUsuario (User request){
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEnabled(true);
        newUser.setRoles(request.getRoles());
        return userRepository.save(newUser);
    }
}
