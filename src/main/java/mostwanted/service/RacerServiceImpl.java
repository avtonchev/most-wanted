package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.RacerImportDTO;
import mostwanted.domain.dtos.TownInputDTO;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RacerServiceImpl implements RacerService {

    private final String RACER_JSON_PATH= String.format("%s" + "\\src\\main\\resources\\files\\racers.json", System.getProperty("user.dir"));
    private final FileUtil fileUtil;
    private final RacerRepository racerRepository;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;

    public RacerServiceImpl(FileUtil fileUtil, RacerRepository racerRepository, ModelMapper modelMapper, TownRepository townRepository, Gson gson, ValidationUtil validationUtil) {
        this.fileUtil = fileUtil;
        this.racerRepository = racerRepository;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }


    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() !=0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        String racersInput= this.fileUtil.readFile(RACER_JSON_PATH);
        return racersInput.trim();
    }

    @Override
    public String importRacers(String racersFileContent) {

        StringBuilder importResult= new StringBuilder();
        RacerImportDTO[] racerImportDTOS= this.gson.fromJson(racersFileContent, RacerImportDTO[].class);

        for (RacerImportDTO racerImportDTO : racerImportDTOS ) {
            if (!this.validationUtil.isValid(racerImportDTO)){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            if (this.racerRepository.findByName(racerImportDTO.getName())== null){
                importResult.append("Error: Duplicate Data!").append(System.lineSeparator());
            }

            Town townEntity= this.townRepository.findByName(racerImportDTO.getHomeTown()).orElse(null);
            if (townEntity==null){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Racer racerEntity=this.modelMapper.map(racerImportDTO, Racer.class);
            racerEntity.setHomeTown(townEntity);
            this.racerRepository.saveAndFlush(racerEntity);

            importResult.append(String.format("Successfully imported Racers – %s.", racerEntity.getName()))
                    .append(System.lineSeparator());

        }
        return importResult.toString().trim();
    }

    @Override
    public String exportRacingCars() {
        StringBuilder exportResult= new StringBuilder();
        List<Racer> racerList= this.racerRepository.racingCarsCollection();
        racerList.stream().filter(x-> x.getAge()!=0).forEach(racer -> {
            exportResult.append(String.format("Name: %s",racer.getName())).append(System.lineSeparator());
            exportResult.append("Cars:").append(System.lineSeparator());
            racer.getCars().stream().forEach(car -> {
                exportResult.append(String.format("%s %s %d", car.getBrand(), car.getModel(), car.getYearOfProduction()))
                        .append(System.lineSeparator());

            });
            exportResult.append(System.lineSeparator());
        });
        return exportResult.toString().trim();
    }
}
