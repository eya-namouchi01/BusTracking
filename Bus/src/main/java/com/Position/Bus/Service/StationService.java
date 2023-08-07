package com.Position.Bus.Service;

import com.Position.Bus.Exeption.UserNotFoundException;
import com.Position.Bus.Model.Circuit;
import com.Position.Bus.Model.Station;
import com.Position.Bus.Repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {
    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

   public List<Station> getAllStations() {
        return stationRepository.findAll();
   }
   public Station getStationByID(Long id) {
       return stationRepository.findById(id)
               .orElseThrow(() -> new UserNotFoundException("Circuit"+id+"was not found"));
    }
   public Station addstation(Station station) {
       return stationRepository.save(station);
   }



//
//    public Station updateStation(Station station) {
//       return this.stationRepository.save(station);}
//
//    public void deleteStation(Long id) {
//        this.stationRepository.deleteById(id);
//    }
}

