package com.Position.Bus.Service;

import com.Position.Bus.Model.Circuit;
import com.Position.Bus.Model.Station;

import java.util.List;
import java.util.Optional;

public interface CircuitServiceInterface {
        String addCircuit(Circuit circuit);
        List<Circuit> getAllCircuits();
        Optional<List<Station>> getCircuitStationsByCircuitId(long id);
        Optional<Circuit> getCircuitById(Long id);
//    Circuit updateCircuit(Circuit circuit);
//    void deleteCircuit(Long id);
//    Circuit addCircuit(Circuit circuit, List<Station> stations);
}
