package neo.uap.service;

import com.fasterxml.jackson.databind.JsonNode;
import neo.uap.domain.system.DataDictionaryItem;
import neo.uap.domain.system.DataDictionaryType;

import java.util.List;

public interface DataDictionaryService {
    List<DataDictionaryType> listType();

    DataDictionaryType getType(Long id);

    DataDictionaryType getParentType(Long id);

    void addType(DataDictionaryType dataDictionaryType);

    void editType(Long id, JsonNode dataDictionaryTypeJson);

    List<DataDictionaryItem> listItemByTypeId(Long typeId);

    List<DataDictionaryItem> listItemByParentItem(String typeName, String parentTypeName, String parentValue);

    void addItem(DataDictionaryItem dataDictionaryItem);

    void editItem(DataDictionaryItem dataDictionaryItem);
}
