package mostwanted.domain.dtos.RaceEntriesDTO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "race-entry ")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryImportDTO {

    @XmlAttribute(name="has-finished")
    private String hasFinished;

    @XmlAttribute(name="finish-time")
    private String finishTime;

    @XmlAttribute(name="car-id")
    private String carId;

    @XmlElement(name="racer")
    private String racerName;

    public RaceEntryImportDTO() {
    }

    public String getHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(String hasFinished) {
        this.hasFinished = hasFinished;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getRacerName() {
        return racerName;
    }

    public void setRacerName(String racerName) {
        this.racerName = racerName;
    }
}


/*
<race-entry has-finished="true" finish-time="741.12" car-id="269">
        <racer> Max Philpott</racer>

 */