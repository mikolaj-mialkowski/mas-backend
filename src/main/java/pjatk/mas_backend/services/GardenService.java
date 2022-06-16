package pjatk.mas_backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pjatk.mas_backend.models.business.GardenBO;
import pjatk.mas_backend.models.entities.GardenEntity;
import pjatk.mas_backend.repositories.GardenRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class GardenService {

    @Value("${mas.backend.garden.name}")
    private String defaultName;

    @Value("${mas.backend.garden.address}")
    private String defaultAddress;

    @Value("${mas.backend.garden.openHours}")
    private String defaultOpenHours;

    private final static Logger LOGGER = LoggerFactory.getLogger(GardenService.class);

    private final GardenRepository gardenRepository;

    public GardenService(GardenRepository gardenRepository) {
        this.gardenRepository = gardenRepository;
    }

    public GardenBO getGarden(){

        GardenEntity gardenEntity  = gardenRepository.findAll()
                .stream().findFirst().orElseGet(this::createGardenEntity);

        return entityToBusinessObject(gardenEntity);
    }

    private GardenEntity createGardenEntity(){
        GardenEntity gardenEntity = GardenEntity.builder()
                .name(defaultName)
                .address(defaultAddress)
                .openHours(defaultOpenHours)
                .build();

        return gardenRepository.saveAndFlush(gardenEntity);
    }

    @PostConstruct
    private void initializeGardenEntity(){
        if ((long) gardenRepository.findAll().size() == 0) {
            GardenEntity gardenEntity = createGardenEntity();
            LOGGER.warn("No base garden found, new one created = "+ gardenEntity);
        }
        else
            LOGGER.warn("Base garden found");
    }
    public GardenBO changeGarden(GardenBO gardenBO){

        if ((long) gardenRepository.findAll().size() == 0)
            createGardenEntity();

        GardenEntity gardenEntity = gardenRepository.findAll().get(0);

        gardenEntity.setName(gardenBO.getName());
        gardenEntity.setAddress(gardenBO.getAddress());
        gardenEntity.setOpenHours(gardenBO.getOpenHours());

        gardenRepository.saveAndFlush(gardenEntity);

        LOGGER.info("Saved new garden, as garden entity = " + gardenEntity);
        return entityToBusinessObject(gardenEntity);
    }

    private GardenEntity businessObjectToEntity(GardenBO gardenBO){
        return GardenEntity.builder()
                .name(gardenBO.getName())
                .address(gardenBO.getAddress())
                .openHours(gardenBO.getOpenHours())
                .build();
    }

    @SuppressWarnings("all")
    private GardenBO entityToBusinessObject(GardenEntity gardenEntity){
        return GardenBO.builder()
                .id(gardenEntity.getId())
                .name(gardenEntity.getName())
                .address(gardenEntity.getAddress())
                .openHours(gardenEntity.getOpenHours())
                .build();
    }
}
