package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sptech.school.nail_api.entity.NailService;

import java.util.List;

public interface NailServiceRepository extends JpaRepository<NailService, Integer> {

    boolean existsByServiceNameIgnoreCase(String serviceName);

    boolean existsByServiceNameIgnoreCaseAndIdNailServiceNot(String serviceName, Integer idNailService);

    @Query("""
            SELECT service
            FROM NailService service
            WHERE service.isActive = true
            ORDER BY service.serviceName ASC
            """)
    List<NailService> findAllActive();

}