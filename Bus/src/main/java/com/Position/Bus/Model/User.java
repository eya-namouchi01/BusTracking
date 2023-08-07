package com.Position.Bus.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
//@Table(name="bus")
@Data
@Getter
@Setter

public class User{
    @Id
@Column(nullable = false , updatable = false)
@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // static public Double longitude;
    private String userCode;
//    private Double UserStationLatitude;
//    private Double UserStationLongitude;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    private Bus bus;


    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

}