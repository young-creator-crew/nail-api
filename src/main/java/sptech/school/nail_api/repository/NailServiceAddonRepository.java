package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.nail_api.entity.NailServiceAddon;

import java.util.List;

public interface NailServiceAddonRepository extends JpaRepository<NailServiceAddon, Integer> {

    List<NailServiceAddon> findAllByNailService_IdNailService(Integer idNailService);

}