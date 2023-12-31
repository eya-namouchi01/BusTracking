package com.Position.Bus.Service;
import com.Position.Bus.Model.*;
import com.Position.Bus.Repository.BusRepository;
import org.springframework.transaction.annotation.Transactional;
import com.Position.Bus.Repository.CircuitRepository;
import com.Position.Bus.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CircuitService implements  CircuitServiceInterface{

    private final BusRepository busRepository;
    private final CircuitRepository circuitRepository;
    private final StationRepository stationRepository;

    @Autowired
    public CircuitService(BusRepository busRepository, CircuitRepository circuitRepository, StationRepository stationRepository) {
        this.busRepository = busRepository;
        this.circuitRepository = circuitRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public String addCircuit(Circuit circuit) {
        return null;
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




       public String addCircuit(CircuitStations circuit) {
           Circuit circuit1 = new Circuit(circuit.getNom());
            circuitRepository.save(circuit1);
           Long maxId = circuitRepository.findMaxId();
           Optional<Circuit> circuitfinal = circuitRepository.findById(maxId);
           System.out.println(maxId);
            for (Station station : circuit.getStations() )
            {
                System.out.println(station);
               Long idStat = station.getId();
              station.setCircuit(circuitfinal.get());

               stationRepository.save(station);
            }
            return "circuit created";
        }


    @Transactional
    public void deleteCircuit(Long id) {
        stationRepository.setStationCircuitIdNull(id);
        circuitRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, String nom, List<Station> stations) {
        Optional<Circuit> c =circuitRepository.findById(id);
        if(c.isPresent())
        {
            Circuit circuit = c.get();
            if(!circuit.getNom().equals(nom))
            {
                circuit.setNom(nom);
                circuitRepository.save(circuit);
            }
            stationRepository.setStationCircuitIdNull(id);
            for (Station station : stations )
            {
                station.setCircuit(circuit);
                stationRepository.save(station);
            }

        }

    }

    public List<Circuit> getCircuitsListNotAffectedTobus() {
        List<Circuit> circuitsListNotAffected = new ArrayList<>();
        List<Bus> busList = busRepository.findAll();
        List<Circuit> circuitList = circuitRepository.findAll();

        for (Circuit circuit : circuitList) {
            boolean exists = false;
            for (Bus bus : busList) {
                Circuit busCircuit = bus.getCircuit();
                if (busCircuit != null && busCircuit.getId() == circuit.getId()) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                circuitsListNotAffected.add(circuit);
            }
        }
        return circuitsListNotAffected;
    }

    public List<Circuit> getCircuitsListNotAffectedAndAffected(Long id) {
        List<Circuit> circuits = getCircuitsListNotAffectedTobus();
        Optional<Bus> busOptional = busRepository.findById(id);
        if(busOptional.isPresent())
        {
            Bus bus = busOptional.get();
            if(bus.getCircuit() != null)
            {
                circuits.add(bus.getCircuit());
            }
        }
        return circuits;

    }
}
