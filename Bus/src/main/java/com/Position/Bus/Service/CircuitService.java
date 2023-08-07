package com.Position.Bus.Service;

import com.Position.Bus.Exeption.UserNotFoundException;
import com.Position.Bus.Model.Circuit;
import com.Position.Bus.Model.CircuitDetails;
import com.Position.Bus.Model.Station;
import com.Position.Bus.Repository.CircuitRepository;
import com.Position.Bus.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CircuitService implements  CircuitServiceInterface{

    private final CircuitRepository circuitRepository;
    private final StationRepository stationRepository;

    @Autowired
    public CircuitService(CircuitRepository circuitRepository, StationRepository stationRepository) {
        this.circuitRepository = circuitRepository;
        this.stationRepository = stationRepository;
    }

    public List<Circuit> getAllCircuits() {
        List<CircuitDetails> circuitDetailsList = circuitRepository.getCircuitDetails();
        List<Circuit> circuits = new ArrayList<>();

        for (CircuitDetails circuitDetails : circuitDetailsList) {
            Circuit circuit = new Circuit(circuitDetails.getId(), circuitDetails.getNom(), circuitDetails.getStations());

            circuits.add(circuit);
        }
       // System.out.println(stationRepository.getListStationById(302L));

        return circuits;
    }

    public List<Station> getCircuitStationsbyCircuitId(Long id)
    {
       return  stationRepository.getListStationById(id);
    }





//    public Circuit getCircuitById(Long id) {
//        return circuitRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("Circuit"+id+"was not found"));
//    }
//
  /*  public Circuit addCircuit(Long StationId,Circuit circuit) {
     Station station =this.stationRepository.findById(StationId)
     .orElseThrow(( )-> new UserNotFoundException("Station with ID"+StationId+"was not found"));}

        circuit.setStations();
        return circuitRepository.save(circuit);*/

       public Circuit addCircuit(Circuit circuit) {
            Circuit circuit1 = new Circuit(circuit.getNom());
            circuitRepository.save(circuit1);
            Long maxId = circuitRepository.findMaxId();
            for (Station station : circuit.getStations() )
            {
               Long idStat = station.getId();
               stationRepository.setIdCircuit(maxId, station.getId());

            }
            return circuit;
        }





//    public Circuit updateCircuit(Circuit circuit) {
//        return this.circuitRepository.save(circuit);}
//
//    public void deleteCircuit(Long id) {
//        this.circuitRepository.deleteById(id);
//    }
}
