package com.Position.Bus.Service;

import com.Position.Bus.Model.Circuit;
import com.Position.Bus.Model.Station;

import java.util.List;

public interface CircuitServiceInterface {
        Circuit addCircuit(Circuit circuit);
        List<Circuit> getAllCircuits();
        List<Station> getCircuitStationsbyCircuitId(Long id);
//    Circuit getCircuitById(Long id);
//    Circuit updateCircuit(Circuit circuit);
//    void deleteCircuit(Long id);
//    Circuit addCircuit(Circuit circuit, List<Station> stations);
}
