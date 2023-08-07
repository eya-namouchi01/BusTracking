package com.Position.Bus.Model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
//@Table(name="bus")
@Data
@Getter
@Setter
public class Station {
    @Id
    @Column(nullable = false , updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String station;

    // static public Double longitude;
    private Double longitude_position;


    private Double latitude_position;
    @ManyToOne  (cascade = CascadeType.ALL)
    @JoinColumn(name="circuit_id", referencedColumnName = "id")
    private Circuit circuit;

}
