package com.example.emaillogtime.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "username khong duoc de trong,co chua khoang trang")
    @Column(name = "username")
    @Email(message = "email khong hop le")
    private String username;
    @Size(min = 6, max = 255,message = "do dai mat khau phai co do dai tu 6-255 ky tu")
    @NotBlank(message = "mat khau khong duoc de trong")
    @Column(name = "password")
    private String password;
//    @JsonIgnore
////    @OneToMany(mappedBy = "account")
////    private Set<Role> roles;
//    @ManyToOne
//    @JoinColumn(name = "role_id")
//    private Role role;
}
