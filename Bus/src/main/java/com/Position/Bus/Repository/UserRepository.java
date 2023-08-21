package com.Position.Bus.Repository;

import com.Position.Bus.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUserCode(String userCode);
    Optional<List<User>> findByBus(Bus bus);

   // Optional<List<User>>  getUsersByBus(Bus bus);
}



