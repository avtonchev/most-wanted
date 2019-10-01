package mostwanted.domain.dtos.RaceDTO;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportDTO {

        @XmlElement(name = "laps")
        private int laps;

        @XmlElement(name = "district-name")
        private String districtName;

        @XmlElement(name = "entries")
        private EntryRootImportDTO entryRootImportDTOS;

        public RaceImportDTO() {
        }

        @NotNull
        public int getLaps() {
                return laps;
        }

        public void setLaps(int laps) {
                this.laps = laps;
        }

        @NotNull
        public String getDistrictName() {
                return districtName;
        }

        public void setDistrictName(String districtName) {
                this.districtName = districtName;
        }

        public EntryRootImportDTO getEntryRootImportDTOS() {
                return entryRootImportDTOS;
        }

        public void setEntryRootImportDTOS(EntryRootImportDTO entryRootImportDTOS) {
                this.entryRootImportDTOS = entryRootImportDTOS;
        }
}