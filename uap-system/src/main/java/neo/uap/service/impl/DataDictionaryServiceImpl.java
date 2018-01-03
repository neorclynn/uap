package neo.uap.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import neo.uap.domain.system.DataDictionaryItem;
import neo.uap.domain.system.DataDictionaryType;
import neo.uap.mapper.DataDictionaryMapper;
import neo.uap.repository.DataDictionaryItemRepository;
import neo.uap.repository.DataDictionaryTypeRepository;
import neo.uap.service.DataDictionaryService;
import neo.uap.util.BeanUtil;
import neo.uap.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {
    @Autowired
    private DataDictionaryMapper dataDictionaryMapper;

    @Autowired
    private DataDictionaryItemRepository dataDictionaryItemRepository;

    @Autowired
    private DataDictionaryTypeRepository dataDictionaryTypeRepository;

    public List<DataDictionaryType> listType() {
        return dataDictionaryTypeRepository.findAll();
    }

    public DataDictionaryType getType(Long id) {
        return dataDictionaryTypeRepository.findOne(id);
    }

    public DataDictionaryType getParentType(Long id) {
        return getType(getType(id).getParentTypeId());
    }

    public void addType(DataDictionaryType dataDictionaryType) {
        dataDictionaryType.setCreatedBy(UserUtil.getCurrentUsername());
        dataDictionaryType.setUpdatedBy(UserUtil.getCurrentUsername());
        dataDictionaryTypeRepository.save(dataDictionaryType);
    }

    public void editType(Long id, JsonNode dataDictionaryTypeJson) {
        DataDictionaryType existedType = getType(id);
        BeanUtil.setChange(existedType, dataDictionaryTypeJson);
        dataDictionaryTypeRepository.save(existedType);
    }

    public List<DataDictionaryItem> listItemByTypeId(Long typeId) {
        return dataDictionaryItemRepository.findByTypeId(typeId);
    }

    public List<DataDictionaryItem> listItemByParentItem(String typeName, String parentTypeName, String parentValue) {
        return dataDictionaryMapper.listItemByParentItem(typeName, parentTypeName, parentValue);
    }

    public void addItem(DataDictionaryItem dataDictionaryItem) {
        dataDictionaryMapper.addItem(dataDictionaryItem);
    }

    public void editItem(DataDictionaryItem dataDictionaryItem) {
        dataDictionaryMapper.editItem(dataDictionaryItem);
    }
}
