package mostwanted.domain.dtos.RaceDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryRootImportDTO {

    @XmlElement(name="entry")
    private EntryImportDTO[] entryImportDTOS;

    public EntryRootImportDTO() {
    }

    public EntryImportDTO[] getEntryImportDTOS() {
        return entryImportDTOS;
    }

    public void setEntryImportDTOS(EntryImportDTO[] entryImportDTOS) {
        this.entryImportDTOS = entryImportDTOS;
    }
}
