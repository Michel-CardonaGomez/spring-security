//package com.usco.demo_security.models;
//
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "user_roles")
//@IdClass(UserRolesId.class)
//public class UserRoles {
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Id
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private Role role;
//
//}