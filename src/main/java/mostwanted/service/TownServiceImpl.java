package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.TownInputDTO;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TownServiceImpl implements TownService {

    private final String TOWNS_JSON_PATH= String.format("%s" + "\\src\\main\\resources\\files\\towns.json", System.getProperty("user.dir"));
    private final TownRepository townRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;


    public TownServiceImpl(TownRepository townRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count()!=0;
    }


    @Override
    public String readTownsJsonFile() throws IOException {
        String resultTowns= this.fileUtil.readFile(TOWNS_JSON_PATH);
        return resultTowns;
    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder importResult= new StringBuilder();
        TownInputDTO[] townInputDTOS= this.gson.fromJson(townsFileContent, TownInputDTO[].class);

        for (TownInputDTO townInputDTO : townInputDTOS) {
            if (!this.validationUtil.isValid(townInputDTO)){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            if (this.townRepository.findByName(townInputDTO.getName())== null){
                importResult.append("Error: Duplicate Data!").append(System.lineSeparator());
            }

            Town townEntity= this.modelMapper.map(townInputDTO, Town.class);
            this.townRepository.saveAndFlush(townEntity);
            importResult.append(String.format("Successfully imported Towns – %s.", townEntity.getName()))
                .append(System.lineSeparator());

        }
        return importResult.toString().trim();
    }

    @Override
    public String exportRacingTowns() {

        StringBuilder exportResult= new StringBuilder();
        List<Town> towns= this.townRepository.racingTownsCount();
        towns.stream().forEach(town -> {
            exportResult.append(String.format("Name: %s", town.getName())).append(System.lineSeparator());
            exportResult.append(String.format("Racers: %d", town.getRacers().size())).append(System.lineSeparator());
            exportResult.append(System.lineSeparator());
        });

      return exportResult.toString().trim();
    }
}
