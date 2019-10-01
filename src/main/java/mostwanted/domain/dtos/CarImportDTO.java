package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class CarImportDTO {

    @Expose
    @SerializedName("brand")
    private String brand;

    @Expose
    @SerializedName("model")
    private String model;

    @Expose
    @SerializedName("price")
    private String price;

    @Expose
    @SerializedName("yearOfProduction")
    private String yearOfProduction;

    @Expose
    @SerializedName("racerName")
    private String racerName;

    public CarImportDTO() {
    }

    @NotNull
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @NotNull
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @NotNull
    public String getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(String yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public String getRacerName() {
        return racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}
