package com.usco.demo_security.controller;

import com.usco.demo_security.Repository.RoleRepository;
import com.usco.demo_security.Repository.UserRepository;
import com.usco.demo_security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.Optional;


@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("user", new User());
        return "registro";
    }

    @PostMapping
    public String registrarUsuario(@ModelAttribute("user") User user, @RequestParam("password") String password, Model model) {
        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());

        if (!userOptional.isPresent()) {
            model.addAttribute("error", "usuario no registrado.");
            return "registro";
        }

        User userExistente = userOptional.get();

        if (userExistente.isEnabled()) {
            model.addAttribute("error", "Ya has creado tu cuenta.");
            return "registro";
        }

        userExistente.setPassword(passwordEncoder.encode(password)); // Encriptar la contraseña
        userExistente.setEnabled(true); // Cambiar el estado de 'activo' a true
        userRepository.save(userExistente);
        return "redirect:/login";
    }

//    @PostMapping("/registro")
//    public String registrarUsuario(@ModelAttribute("user") User user,
//                                   String[] roles,
//                                   Model model) {
//        // Validar si el nombre de usuario ya existe
//        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
//        if (userOptional.isPresent()) {
//            model.addAttribute("error", "El usuario ya está registrado.");
//            return "registro"; // Volver al formulario de registro
//        }
//
//        // Establecer la contraseña encriptada y habilitar al usuario
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setEnabled(true);
//
//        // Asignar roles seleccionados
//        Set<Role> roleSet = new HashSet<>();
//        for (String roleName : roles) {
//            Optional<Role> roleOptional = roleRepository.findByName(roleName);
//            roleOptional.ifPresent(roleSet::add);
//        }
//        user.setRoles(roleSet); // Asignar roles al usuario
//
//        // Guardar el usuario
//        userRepository.save(user);
//        return "redirect:/login"; // Redirigir a la página de login
//    }

}
