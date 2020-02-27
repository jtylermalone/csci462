package com.techprimers.db.repository;

import com.techprimers.db.model.Containers;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContainersRepository extends JpaRepository<Containers, Integer> {
    List<Containers> findByName(String name);

    List<Containers> findById(Integer id);
}
