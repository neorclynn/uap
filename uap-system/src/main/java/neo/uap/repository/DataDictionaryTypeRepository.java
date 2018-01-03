package neo.uap.repository;

import neo.uap.domain.system.DataDictionaryType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataDictionaryTypeRepository extends JpaRepository<DataDictionaryType, Long> {
}
