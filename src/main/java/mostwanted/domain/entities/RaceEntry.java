package mostwanted.domain.entities;

import mostwanted.domain.entities.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="race_entries")
public class RaceEntry extends BaseEntity {

    private boolean hasFinished;
    private double finishTime;
    private Car car;
    private Racer racer;
    private Race race;

    public RaceEntry() {
    }

    public boolean isHasFinished() {
        return hasFinished;
    }
    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public double getFinishTime() {
        return finishTime;
    }
    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    @ManyToOne
    @JoinColumn(name="car_id", referencedColumnName = "id")
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }


    @ManyToOne
    @JoinColumn(name="racer_id", referencedColumnName = "id")
    public Racer getRacer() {
        return racer;
    }
    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    @ManyToOne
    @JoinColumn(name="race_id", referencedColumnName = "id")
    public Race getRace() {
        return race;
    }
    public void setRace(Race race) {
        this.race = race;
    }
}


/*
•	hasFinished – a boolean value.
•	finishTime – a floating-point data type.
•	car – a Car entity.
•	racer – a Racer entity.

 */