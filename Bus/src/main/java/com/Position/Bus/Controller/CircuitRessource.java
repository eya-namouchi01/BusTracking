package com.Position.Bus.Controller;


import com.Position.Bus.Model.Bus;
import com.Position.Bus.Model.Circuit;
import com.Position.Bus.Model.CircuitStations;
import com.Position.Bus.Model.Station;
import com.Position.Bus.Service.CircuitService;
import com.Position.Bus.Service.CircuitServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/Circuit")
@CrossOrigin(origins = "*")
public class CircuitRessource {
    private final CircuitService circuitService;

    @Autowired
    public CircuitRessource(CircuitService circuitService) {
        this.circuitService = (CircuitService) circuitService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCircuit(@RequestBody CircuitStations circuit) {
            return new ResponseEntity<> (this.circuitService.addCircuit(circuit), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Circuit>> getAllCircuits() {
        List<Circuit> circuits=this.circuitService.getAllCircuits();
        return new ResponseEntity<>(circuits,HttpStatus.OK);
    }

    @GetMapping("/StationsbyCircuitId/{id}")
    public ResponseEntity<List<Station>> getStationsbyCircuitId(@PathVariable Long id) {
        Optional<List<Station>> circuits=this.circuitService.getCircuitStationsByCircuitId(id);

        return new ResponseEntity<>(circuits.get(),HttpStatus.OK);
    }



    @GetMapping("/CircuitById/{id}")
    public ResponseEntity<Circuit>getCircuitById(@PathVariable Long id) {
        Optional<Circuit> circuit = circuitService.getCircuitById(id);
        return new ResponseEntity(circuit, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteCircuit(@PathVariable Long id)
    {
        circuitService.deleteCircuit(id);
    }


    @PutMapping("/{id}/{nom}")
    public void updateCircuit(@PathVariable Long id,@PathVariable String nom, @RequestBody List<Station> stations)
    {
        System.out.println(nom);

        circuitService.update(id,nom,stations);
    }


    }






