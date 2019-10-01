package mostwanted.domain.dtos.RaceDTO;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportDTO {

    @XmlAttribute(name="id")
    private String id;

    public EntryImportDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
