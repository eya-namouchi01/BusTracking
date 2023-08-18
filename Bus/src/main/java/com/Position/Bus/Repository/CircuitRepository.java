package com.Position.Bus.Repository;
import com.Position.Bus.Model.Circuit;
import com.Position.Bus.Model.CircuitDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CircuitRepository extends JpaRepository<Circuit,Long> {

    Optional<Circuit> findById(Long id);
    @Query("SELECT MAX(c.id) FROM Circuit c")
    Long findMaxId();


    @Query("SELECT NEW com.Position.Bus.Model.CircuitDetails(c.id, c.nom) FROM Circuit c")
    List<CircuitDetails> getCircuitDetails();

    


}