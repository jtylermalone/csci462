package com.scpa.db.repository;

import com.scpa.db.model.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VesselRepository extends JpaRepository<Vessel, Integer> {
    
    @Query(value = "SELECT * FROM vessel v WHERE v.VR_VESSEL_FIRST_FO = :line_code", nativeQuery = true)
    Vessel findByVesselCode(
        @Param("line_code") String line_code);

}
