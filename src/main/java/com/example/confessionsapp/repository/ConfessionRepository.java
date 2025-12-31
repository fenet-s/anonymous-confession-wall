package com.example.confessionsapp.repository;

import com.example.confessionsapp.model.Confession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfessionRepository extends JpaRepository<Confession, Long> {
    // This allows you to find all confessions from the database
}