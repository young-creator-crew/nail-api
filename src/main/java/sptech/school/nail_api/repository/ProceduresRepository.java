package sptech.school.nail_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sptech.school.nail_api.model.Procedure;

public interface ProceduresRepository extends JpaRepository<Procedure, Integer> {
    Boolean existsByNameIgnoreCase(String name);
    Boolean existsByNameIgnoreCaseAndIdNot(String name, Integer id);
}
