package com.Position.Bus.Service;

import com.Position.Bus.Model.Station;

import java.util.List;

public interface StationServiceInterface {
  List<Station> getAllStations();
  Station getStationByID(Long id);
  Station addstation(Station station);

  void deleteStation(Long id);
//    Station updateStation(Station station);
//    void deleteStation(Long id);

}
