package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class RacerImportDTO {

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("age")
    private int age;

    @Expose
    @SerializedName("homeTown")
    private String homeTown;

    @Expose
    @SerializedName("bounty")
    private String bounty;

    public RacerImportDTO() {

    }


    public String getBounty() {
        return bounty;
    }
    public void setBounty(String bounty) {
        this.bounty = bounty;
    }

    @NotNull
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

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}
