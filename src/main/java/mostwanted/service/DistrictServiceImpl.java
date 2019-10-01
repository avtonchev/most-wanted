package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.DistrictImportDTO;
import mostwanted.domain.dtos.TownInputDTO;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final String DISTRICT_JSON_PATH= String.format("%s" + "\\src\\main\\resources\\files\\districts.json", System.getProperty("user.dir"));
    private final DistrictRepository districtRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;



    public DistrictServiceImpl(DistrictRepository districtRepository, FileUtil fileUtil, ModelMapper modelMapper, TownRepository townRepository, Gson gson, ValidationUtil validationUtil) {
        this.districtRepository = districtRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() !=0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        String inputDistricts= this.fileUtil.readFile(DISTRICT_JSON_PATH);
        return inputDistricts.trim();
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        StringBuilder importResult= new StringBuilder();
        DistrictImportDTO[] districtImportDTOS= this.gson.fromJson(districtsFileContent, DistrictImportDTO[].class);

        for (DistrictImportDTO districtImportDTO : districtImportDTOS) {
            if (!this.validationUtil.isValid(districtImportDTO)){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            if (this.townRepository.findByName(districtImportDTO.getName())== null){
                    importResult.append("Incorrect Data").append(System.lineSeparator());
                    continue;
            }
            if (this.districtRepository.findByName(districtImportDTO.getName()).orElse(null)!=null){
                continue;
            }
            Town townEntity= this.townRepository.findByName(districtImportDTO.getTownName()).orElse(null);

            District districtEntity= this.modelMapper.map(districtImportDTO, District.class);

            districtEntity.setTown(townEntity);

            this.districtRepository.saveAndFlush(districtEntity);
            importResult.append(String.format("Successfully imported District – %s.", districtEntity.getName()))
                    .append(System.lineSeparator());

        }
        return importResult.toString().trim();
    }
}
