package mostwanted.domain.entities;

import mostwanted.domain.entities.District;
import mostwanted.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Entity(name="races")
public class Race extends BaseEntity {

    private int laps;
    private District district;
    private List<RaceEntry> raceEntriesList;


    @Column(nullable = false, columnDefinition = "int default 0")
    public int getLaps() {
        return laps;
    }
    public void setLaps(int laps) {
        this.laps = laps;
    }

    @ManyToOne
    @JoinColumn(name="district_id", referencedColumnName = "id")
    public District getDistrict() {
        return district;
    }
    public void setDistrict(District district) {
        this.district = district;
    }

    @OneToMany(mappedBy = "race" )
    public List<RaceEntry> getRaceEntriesList() {
        return raceEntriesList;
    }
    public void setRaceEntriesList(List<RaceEntry> raceEntriesList) {
        this.raceEntriesList = raceEntriesList;
    }
}

/*
•	id – integer number, primary identification field.
•	laps – integer number (required, default – 0)
•	district – a District entity (required).
•	entries – a collection of RaceEntry entity.

 */