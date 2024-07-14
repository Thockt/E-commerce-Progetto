package com.example.prodotti.handler;

import jakarta.persistence.Entity;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GenericResponse {

    private String messagge;
}
