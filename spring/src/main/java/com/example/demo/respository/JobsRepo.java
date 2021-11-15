package com.example.demo.respository;

import com.example.demo.Entity.Jobs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobsRepo extends JpaRepository<Jobs,Long> {
}
