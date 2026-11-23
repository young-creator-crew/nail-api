package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.nail_api.entity.NailServiceMedia;

import java.util.List;

public interface NailServiceMediaRepository extends JpaRepository<NailServiceMedia, Long> {

    List<NailServiceMedia> findByNailService_IdNailService(Integer idNailService);

}
