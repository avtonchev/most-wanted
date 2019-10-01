package mostwanted.domain.entities;

import mostwanted.domain.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class District extends BaseEntity {

    private String name;
    private Town town;

    @Column(nullable = false, unique = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @ManyToOne
    @JoinColumn(name= "town_id", referencedColumnName = "id")
    public Town getTown() {
        return town;
    }
    public void setTown(Town town) {
        this.town = town;
    }
}
