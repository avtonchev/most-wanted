package mostwanted.domain.dtos.RaceEntriesDTO;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntryRootImportDTO {

    @XmlElement(name="race-entry")
    private RaceEntryImportDTO[] raceEntryImportDTOSDTOS;

    public RaceEntryRootImportDTO() {
    }

    public RaceEntryImportDTO[] getRaceEntryImportDTOSDTOS() {
        return raceEntryImportDTOSDTOS;
    }

    public void setRaceEntryImportDTOSDTOS(RaceEntryImportDTO[] raceEntryImportDTOSDTOS) {
        this.raceEntryImportDTOSDTOS = raceEntryImportDTOSDTOS;
    }
}
