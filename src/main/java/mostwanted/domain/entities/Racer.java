package mostwanted.domain.entities;

import mostwanted.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name="racers")
public class Racer extends BaseEntity {

    private String name;
    private int age;
    private BigDecimal bounty;
    private Town homeTown;
    private List<Car> cars;


    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }
    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    @ManyToOne
    @JoinColumn(name="town_id", referencedColumnName = "id")
    public Town getHomeTown() {
        return homeTown;
    }
    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    @OneToMany(mappedBy = "racer")
    public List<Car> getCars() {
        return cars;
    }
    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}

/*
•	name – a string (required, unique).
•	age – an integer number.
•	bounty – a decimal data type.
•	homeTown – a Town entity.
•	cars – a collection of Car entity.

 */

