package com.Position.Bus.Service;

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


        return circuits;
        // System.out.println(stationRepository.getListStationById(302L));
       // return circuitRepository.findAll();
    }

    @Override
    public Optional<List<Station>> getCircuitStationsByCircuitId(long id) {
        Optional<Circuit> circuit = circuitRepository.findById(id);

        if (circuit.isPresent()) {
            Optional<List<Station>> stations = stationRepository.getStationsByCircuit(circuit.get());
            return stations;
        }
        return null;
    }




    public Optional<Circuit> getCircuitById(Long id)
    {
       return circuitRepository.findById(id);
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

       public String addCircuit(Circuit circuit) {
            Circuit circuit1 = new Circuit(circuit.getNom());
            circuitRepository.save(circuit1);
            Long maxId = circuitRepository.findMaxId();
            for (Station station : circuit.getStations() )
            {
               Long idStat = station.getId();
               stationRepository.setIdCircuit(maxId, station.getId());

            }
            return "circuit created";
        }






}
