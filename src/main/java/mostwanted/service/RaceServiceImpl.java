package mostwanted.service;

import mostwanted.common.Constants;
import mostwanted.domain.dtos.RaceDTO.EntryImportDTO;
import mostwanted.domain.dtos.RaceDTO.EntryRootImportDTO;
import mostwanted.domain.dtos.RaceDTO.RaceImportDTO;
import mostwanted.domain.dtos.RaceDTO.RaceRootImportDTO;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.apache.tomcat.util.bcel.Const;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {

    private final String RACES_XML_PATH= String.format("%s" + "\\src\\main\\resources\\files\\races.xml", System.getProperty("user.dir"));
    private final FileUtil fileUtil;
    private final RaceRepository raceRepository;
    private final DistrictRepository districtRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final RaceEntryRepository raceEntryRepository;

    public RaceServiceImpl(FileUtil fileUtil, RaceRepository raceRepository, DistrictRepository districtRepository, ModelMapper modelMapper, ValidationUtil validationUtil, XmlParser xmlParser, RaceEntryRepository raceEntryRepository) {
        this.fileUtil = fileUtil;
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.raceEntryRepository = raceEntryRepository;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() !=0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        String racesInput= this.fileUtil.readFile(RACES_XML_PATH);
        return racesInput.trim();
    }

    @Override
    public String importRaces() throws JAXBException, FileNotFoundException {
        StringBuilder importResult= new StringBuilder();

       RaceRootImportDTO raceRootImportDTO= this.xmlParser.parseXml(RaceRootImportDTO.class, RACES_XML_PATH);
        for (RaceImportDTO raceImportDTO : raceRootImportDTO.getRaceImportDTOS()) {
            Race raceEntity= this.modelMapper.map(raceImportDTO, Race.class);

            District districtEntity= this.districtRepository.findByName(raceImportDTO.getDistrictName()).orElse(null);
            raceEntity.setDistrict(districtEntity);
            List<RaceEntry> raceEntryList= new ArrayList<>();
            for (EntryImportDTO entryImportDTO : raceImportDTO.getEntryRootImportDTOS().getEntryImportDTOS()) {
                RaceEntry raceEntryEntity= this.raceEntryRepository.findById(Long.parseLong(entryImportDTO.getId())).orElse(null);
                raceEntryEntity.setRace(raceEntity);
                raceEntryList.add(raceEntryEntity);
            }

            this.raceRepository.saveAndFlush(raceEntity);
            this.raceEntryRepository.saveAll(raceEntryList);


        }


        return null;
    }
}
