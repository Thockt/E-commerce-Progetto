package com.example.utenti.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class IndirizzoRequest {

    private Long idUtente;
    private String via;
    private Integer civico;
    private String cap;
    private String comune;
}
