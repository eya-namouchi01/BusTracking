package com.Position.Bus.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity

@Getter
@Setter

public class Circuit {
    @Id
    @Column(nullable = false , updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;


    @OneToMany(mappedBy = "circuit")
    private List<Station> stations;

    public Circuit(String nom) {
        this.nom = nom;
    }

    public Circuit() {

    }

    public Circuit(Long id, String nom, List<Station> stations) {
        this.id = id;
        this.nom = nom;
        this.stations = stations;
    }

    public Circuit(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }
}
