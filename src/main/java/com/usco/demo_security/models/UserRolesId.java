//package com.usco.demo_security.models;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//public class UserRolesId implements Serializable {
//    private Long user_id;  // Debes usar el tipo correcto que sea el mismo que en la entidad
//    private Long role_id;  // Debes usar el tipo correcto que sea el mismo que en la entidad
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof UserRolesId)) return false;
//        UserRolesId that = (UserRolesId) o;
//        return Objects.equals(user_id, that.user_id) &&
//                Objects.equals(role_id, that.role_id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(user_id, role_id);
//    }
//}
