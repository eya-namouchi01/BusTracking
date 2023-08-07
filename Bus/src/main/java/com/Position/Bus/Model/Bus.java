package com.Position.Bus.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

@Entity
//@Table(name="bus")
@Data
@Getter
@Setter

public class Bus implements Serializable {
    @Id
    @Column(nullable = false , updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
  //  private Long numChauffeur;

   // static public Double longitude;
    private Double longitude;
    private Double latitude;


    @JsonIgnore
    @OneToMany(mappedBy = "bus")
    private List<User> users;


//    @OneToOne
//    private Circuit circuit;

    // @Column(name="position")
//    Map<String, Double> position;


    public List<User> getUsers() {
        return this.users;
    }
}
