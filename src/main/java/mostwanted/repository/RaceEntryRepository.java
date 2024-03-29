package mostwanted.repository;

import mostwanted.domain.entities.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Long> {


    Optional<RaceEntry> findById(Long aLong);
}
