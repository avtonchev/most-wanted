package mostwanted.domain.entities;

import mostwanted.domain.entities.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="cars")
public class Car extends BaseEntity {

    private String brand;
    private String model;
    private BigDecimal price;
    private int yearOfProduction;
    private double maxSpeed;
    private double zeroToSixty;
    private Racer racer;

    public Car() {
    }

    @Column(nullable = false)
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(nullable = false)
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }


    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Column(name="year_of_production",nullable = false)
    public int getYearOfProduction() {
        return yearOfProduction;
    }
    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Column(name="max_speed")
    public double getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Column(name="zero_to_sixty")
    public double getZeroToSixty() {
        return zeroToSixty;
    }
    public void setZeroToSixty(double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    @ManyToOne
    @JoinColumn(name="racer_id", referencedColumnName = "id")
    public Racer getRacer() {
        return racer;
    }
    public void setRacer(Racer racer) {
        this.racer = racer;
    }
}

