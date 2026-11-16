package sptech.school.nail_api.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.nail_api.service.domain.NailServiceOption;

import java.util.List;

public interface NailServiceOptionRepository extends JpaRepository<NailServiceOption, Integer> {

    List<NailServiceOption> findAllByNailService_IdNailService(Integer idNailService);

}