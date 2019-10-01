package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class DistrictImportDTO {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("townName")
    private String townName;

    public DistrictImportDTO() {
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
