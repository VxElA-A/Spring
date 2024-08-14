package com.example.DZSeven.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_roles")
@Data
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;
}


//@Data
//@Entity
//@Table(name = "user_roles")
//public class UserRole {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @Column(name = "user_roles_id")
//    private String userRolesId;
//
//
//    @ManyToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
//    private Role role;
//
//
//}

