package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.nail_api.entity.NailServiceImg;

import java.util.List;

public interface NailServiceMediaRepository extends JpaRepository<NailServiceImg, Long> {

    List<String> findByAllFkService(Long fk);

}
