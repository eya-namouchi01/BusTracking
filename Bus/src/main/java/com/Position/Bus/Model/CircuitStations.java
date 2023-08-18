package com.Position.Bus.Model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CircuitStations {
    private Long id;
    private String nom;
    private List<Station> stations;
}
