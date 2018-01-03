package neo.uap.repository;

import neo.uap.domain.system.DataDictionaryItem;
import neo.uap.domain.system.DataDictionaryType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataDictionaryItemRepository extends JpaRepository<DataDictionaryItem, Long> {
    List<DataDictionaryItem> findByTypeId(Long typeId);
}
