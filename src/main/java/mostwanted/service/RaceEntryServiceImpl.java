package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.dtos.RaceEntriesDTO.RaceEntryImportDTO;
import mostwanted.domain.dtos.RaceEntriesDTO.RaceEntryRootImportDTO;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.*;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final String RACE_ENTRIES_XML_PATH= String.format("%s" + "\\src\\main\\resources\\files\\race-entries.xml", System.getProperty("user.dir"));
    private final FileUtil fileUtil;
    private final RaceEntryRepository raceEntryRepository;
    private final ModelMapper modelMapper;
     private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;



    public RaceEntryServiceImpl(FileUtil fileUtil, RaceEntryRepository raceEntryRepository, ModelMapper modelMapper, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, XmlParser xmlParser, CarRepository carRepository, RacerRepository racerRepository, RaceRepository raceRepository) {
        this.fileUtil = fileUtil;
        this.raceEntryRepository = raceEntryRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;

    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() !=0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        String raceEntriesInput= this.fileUtil.readFile(RACE_ENTRIES_XML_PATH);
        return raceEntriesInput.trim();
    }

    @Override
    public String importRaceEntries() throws JAXBException, FileNotFoundException {
        StringBuilder importResult= new StringBuilder();
        RaceEntryRootImportDTO raceEntryRootImportDTO= this.xmlParser.parseXml(RaceEntryRootImportDTO.class, RACE_ENTRIES_XML_PATH);
        for (RaceEntryImportDTO raceEntryImportDTO : raceEntryRootImportDTO.getRaceEntryImportDTOSDTOS()) {

            if (!this.validationUtil.isValid(raceEntryImportDTO)){
                importResult.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
            }

            RaceEntry raceEntryEntity= this.modelMapper.map(raceEntryImportDTO, RaceEntry.class);
            Car carEntity = this.carRepository.findById(Long.parseLong(raceEntryImportDTO.getCarId())).orElse(null);
            Racer racerEntity= this.racerRepository.findByName(raceEntryEntity.getRacer().getName()).orElse(null);

            raceEntryEntity.setCar(carEntity);
            raceEntryEntity.setRacer(racerEntity);
            raceEntryEntity.setRace(null);

            this.raceEntryRepository.saveAndFlush(raceEntryEntity);
            importResult.append(String.format("Successfully imported RaceEntries – %s.", raceEntryEntity.getId()));
        }
        return null;
    }
}
