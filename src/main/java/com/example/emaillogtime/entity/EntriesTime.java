package com.example.emaillogtime.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "entries_Time")
public class EntriesTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entries_timeId")
    private Long entriesTimeId;
    @Column(name = "hours")
    private float hours;
    @CreationTimestamp
    @Column(name = "date")
    private Date date;
    @Column(name = "tweek")
    @Transient
    private float sumHours = 44;
    @Transient
    private float gioThieu = sumHours - ((getHours() * 5) +4);

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

}
