package com.stockproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comenzi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Produse produse;

    @Column(name = "nume_client")
    private String numeClient;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_comanda")
    private StatusComanda statusComanda;
}
