package com.scpa.db.repository;

import com.scpa.db.model.Containers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ContainersRepository extends JpaRepository<Containers, Integer> {
    List<Containers> findById(Integer id);

    List<Containers> findByBadgeNumber(String badge_number);

    List<Containers> deleteById(int id);

    List<Containers> findByShipNumber(String ship_number);

    @Query(value = "SELECT * FROM containers WHERE Transmitted_Datetime > :datetimeStart AND Transmitted_Datetime < :datetimeFinish AND Badge_Num = :badge_number AND Ship_Num = :ship_number AND Crane_Num = :crane_number", nativeQuery = true)
    List<Containers> findByTransmittedDatetimeBetween(
        @Param("datetimeStart") Date datetimeStart,
        @Param("datetimeFinish") Date datetimeFinish,
        @Param("badge_number") String badge_number,
        @Param("ship_number") String ship_number,
        @Param("crane_number") String crane_number
    );
}
