package com.Position.Bus.Repository;

import com.Position.Bus.Model.Circuit;
import com.Position.Bus.Model.Station;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StationRepository extends JpaRepository<Station,Long> {

    Optional<Station> findById(Long id);
    @Query("SELECT s FROM Station s WHERE s.circuit IS NULL")
    List<Station> findAllByCircuitIsNull();

    @Modifying
    @Transactional
    @Query("UPDATE Station s SET s.circuit.id = :idCircuit WHERE s.id = :idStation")
    void setIdCircuit(Long idCircuit, Long idStation);

  /*  @Query("SELECT s FROM Station s WHERE s.circuit.id = :id")
    List<Station> getListStationById(@Param("id") Long id);*/

   Optional<List<Station>>  getStationsByCircuit(Circuit circuit);





}

