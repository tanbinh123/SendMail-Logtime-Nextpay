package com.example.emaillogtime.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "EntriesTimeDTO")
public class EntriesTimeDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entriesTimeDtoId")
    private Long entriesTimeDtoId;
//    @NotBlank(message = "Khong duoc de trong gio")
//    @Size(min = 1, max = 100,message = "do dai phai tu 1-100")
    @Column(name = "timeWeekDTO")
    private float timeWeekDto;
}
