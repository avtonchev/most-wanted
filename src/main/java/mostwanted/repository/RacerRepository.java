package mostwanted.repository;

import mostwanted.domain.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Long> {


    Optional<Racer> findByName(String name);

    @Query("SELECT r FROM mostwanted.domain.entities.Racer r JOIN r.cars GROUP BY r.id ORDER BY size(r.cars) DESC," +
            "r.name ASC ")
    List<Racer> racingCarsCollection();
}
