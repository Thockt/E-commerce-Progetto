package com.example.ordini.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "carrello")
    private List<Ordine> ordineList;
    @Column
    private Long idUtente;
    @Column
    private Double totalAmount;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate;
    @LastModifiedDate
    @Column
    private LocalDateTime lastUpdateTime;

}
