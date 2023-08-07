package com.Position.Bus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class CircuitDetails {
    private Long id;
    private String nom;
    @JsonIgnore
    private List<Station> stations;

    public CircuitDetails(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public CircuitDetails(Long id, String nom, List<Station> stations) {
        this.id = id;
        this.nom = nom;
        this.stations = stations;
    }
}