package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.CarImportDTO;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CarServiceImlp implements CarService {

    private final String CAR_JSON_PATH= String.format("%s" + "\\src\\main\\resources\\files\\cars.json", System.getProperty("user.dir"));
    private final CarRepository carRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final RacerRepository racerRepository;

    public CarServiceImlp(CarRepository carRepository, FileUtil fileUtil, ModelMapper modelMapper, TownRepository townRepository, Gson gson, ValidationUtil validationUtil, RacerRepository racerRepository) {
        this.carRepository = carRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.racerRepository = racerRepository;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count()!=0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        String carsInput= this.fileUtil.readFile(CAR_JSON_PATH);
        return carsInput.trim();
    }

    @Override
    public String importCars(String carsFileContent) {
        StringBuilder importResult = new StringBuilder();
        CarImportDTO[] carImportDTOS = this.gson.fromJson(carsFileContent, CarImportDTO[].class);

        for (CarImportDTO carImportDTO : carImportDTOS) {
            if (!this.validationUtil.isValid(carImportDTO)){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

//
//            if (this.carRepository.findByName(carImportDTO.getBrand()) == null){
//                importResult.append("Error: Duplicate Data!").append(System.lineSeparator());
//            }

                Racer racerEntity = this.racerRepository.findByName(carImportDTO.getRacerName()).orElse(null);
                Car carEntity = this.modelMapper.map(carImportDTO, Car.class);
                carEntity.setRacer(racerEntity);
                this.carRepository.saveAndFlush(carEntity);

                importResult.append(String.format("Successfully imported Cars – %s %s @ %s.", carEntity.getBrand()
                        , carEntity.getModel(), carEntity.getYearOfProduction()))
                        .append(System.lineSeparator());

            }
        return importResult.toString().trim();
        }
}

