package com.Position.Bus.Controller;


import com.Position.Bus.Model.Bus;
import com.Position.Bus.Model.Circuit;
import com.Position.Bus.Model.Station;
import com.Position.Bus.Service.CircuitService;
import com.Position.Bus.Service.CircuitServiceInterface;
import com.Position.Bus.Service.StationService;
import com.Position.Bus.Service.StationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Station")
@CrossOrigin(origins = "*")
public class StationRessource {
    private final StationService stationService;

    @Autowired
    public StationRessource(StationService stationService) {
        this.stationService = stationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Station> addStation(@RequestBody Station station ) {
        return new ResponseEntity<> (this.stationService.addstation(station), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Station>> getAllStations() {
        List<Station> stations=this.stationService.getAllStations();
        return new ResponseEntity<>(stations,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Circuit>getStationByID(@PathVariable Long id) {
        Station station = stationService.getStationByID(id);
        return new ResponseEntity(station, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void  deleteStation(@PathVariable Long id)
    {
        stationService.deleteStation(id);
    }

    @PutMapping("/{id}")
    public void updateStation(@RequestBody Station station, @PathVariable Long id)
    {
        stationService.updateStation(id,station);

    }


//    @PutMapping("/editCircuit/{circuitId}")
//    public ResponseEntity<Station> updateStation(@RequestBody Station station){
//        return new ResponseEntity<> (this.stationService.updateStation(station), HttpStatus.CREATED);
//    }
}






