package com.example.emaillogtime.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    @Column(name = "mail_notification")
    private String mail;

    @OneToMany(mappedBy = "user")
    private Set<EntriesTime> entriesTimeSet;

//    @Transient
//    private float sum = 44;
//    @Transient
//    private float gioThieu = sum - ((getLogTime() * 5) +4);

}
