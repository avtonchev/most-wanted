package mostwanted.repository;

import mostwanted.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Long> {

        Optional<Town> findByName (String name);

        @Query("SELECT t from mostwanted.domain.entities.Town t " +
                "JOIN t.racers r " +
                "WHERE size(r.id) >0  GROUP BY t ORDER BY COUNT (r) DESC, t.name ASC")
        List<Town> racingTownsCount();


        /*
         @Query("SELECT e " +
            "FROM app.ccb.domain.entities.Employee e " +
            "JOIN e.clients c " +
            "WHERE size(c.id) > 0 " +
            "GROUP BY e.id ORDER BY count (c.id) DESC , e.id ASC ")
    List<Employee> exportTopEmployees();
}
         */
}
