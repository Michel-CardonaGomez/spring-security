package com.usco.demo_security.controller;

import com.usco.demo_security.Repository.RoleRepository;
import com.usco.demo_security.Repository.UserRepository;
import com.usco.demo_security.models.Role;
import com.usco.demo_security.models.User;
import com.usco.demo_security.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/registro")
public class RegistroController {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String mostrarFormularioDeRegistro(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("user", new User());
        return "registro";
    }

    @PostMapping
    public String registrarUsuario(@ModelAttribute("user") User user, @RequestParam("password") String password, Model model) {

        Set<Role> roles = user.getSelectedRoleIds().stream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(() -> new RuntimeException("Role no encontrado")))
                .collect(Collectors.toSet());

        if (user.isEnabled()) {
            model.addAttribute("error", "Ya has creado tu cuenta.");
            return "registro";
        }

        try {
            user.setRoles(roles);
            usuarioService.crearUsuario(user);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Error al registrar el usuario: " + e.getMessage());
            return "registro"; // Redirige a la misma vista sin perder el mensaje de error
        }
    }
}
