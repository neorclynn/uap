package neo.uap.mapper;

import neo.uap.domain.system.DataDictionaryItem;
import neo.uap.domain.system.DataDictionaryType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DataDictionaryMapper {
    List<DataDictionaryType> listType(@Param(value = "condition") DataDictionaryType condition);

    DataDictionaryType getType(@Param(value = "id") Long id);

    int editType(@Param(value = "dataDictionaryType") DataDictionaryType dataDictionaryType);

    List<DataDictionaryItem> listItemByTypeName(@Param(value = "typeName") String typeName);

    List<DataDictionaryItem> listItemByParentItem(@Param(value = "typeName") String typeName,
                                                  @Param(value = "parentTypeName") String parentTypeName, @Param(value = "parentValue") String parentValue);

    int addItem(@Param(value = "dataDictionaryItem") DataDictionaryItem dataDictionaryItem);

    int editItem(@Param(value = "dataDictionaryItem") DataDictionaryItem dataDictionaryItem);
}
