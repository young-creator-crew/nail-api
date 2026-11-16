package sptech.school.nail_api.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.nail_api.service.domain.NailServiceAddon;

import java.util.List;

public interface NailServiceAddonRepository extends JpaRepository<NailServiceAddon, Integer> {

    List<NailServiceAddon> findAllByNailService_IdNailService(Integer idNailService);

}