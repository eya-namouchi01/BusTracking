package com.Position.Bus.Controller;


import com.Position.Bus.Model.Bus;
import com.Position.Bus.Model.Circuit;
import com.Position.Bus.Model.Station;
import com.Position.Bus.Service.CircuitService;
import com.Position.Bus.Service.CircuitServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Circuit")
@CrossOrigin(origins = "*")
public class CircuitRessource {
    private final CircuitService circuitService;

    @Autowired
    public CircuitRessource(CircuitService circuitService) {
        this.circuitService = (CircuitService) circuitService;
    }

    @PostMapping("/addCircuit")
    public ResponseEntity<Circuit> addCircuit(@RequestBody Circuit circuit) {
            return new ResponseEntity<> (this.circuitService.addCircuit(circuit), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Circuit>> getAllCircuits() {
        List<Circuit> circuits=this.circuitService.getAllCircuits();
        return new ResponseEntity<>(circuits,HttpStatus.OK);
    }

    @GetMapping("/StationsbyCircuitId/{id}")
    public ResponseEntity<List<Station>> getStationsbyCircuitId(@PathVariable Long id) {
        List<Station> circuits=this.circuitService.getCircuitStationsbyCircuitId(id);
        return new ResponseEntity<>(circuits,HttpStatus.OK);
    }
    @GetMapping("/hello/{id}")
    public String hello(@PathVariable String id) {
        return "hello id"+id;
    }



    @GetMapping("/getCircuitById/{id}")
    public ResponseEntity<List<Station>>getCircuitById(@PathVariable Long id) {
        List<Station> stations = circuitService.getCircuitStationsbyCircuitId(id);
        return new ResponseEntity(stations, HttpStatus.OK);
    }

//
//    @PutMapping("/editCircuit/{circuitId}")
//    public ResponseEntity<Circuit> updateCircuit(@RequestBody Circuit circuit){
//        return new ResponseEntity<> (this.circuitService.updateCircuit(circuit), HttpStatus.CREATED);
//    }

    }






