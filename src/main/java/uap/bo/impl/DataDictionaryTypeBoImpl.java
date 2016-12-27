package uap.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import uap.bo.DataDictionaryTypeBo;
import uap.dao.DataDictionaryTypeDao;
import uap.domain.DataDictionaryType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Service("DataDictionaryTypeBo")
public class DataDictionaryTypeBoImpl implements DataDictionaryTypeBo {
    private DataDictionaryTypeDao dataDictionaryTypeDao;

    @Autowired
    @Required
    public void setUserDao(DataDictionaryTypeDao dataDictionaryTypeDao) {
        this.dataDictionaryTypeDao = dataDictionaryTypeDao;
    }

    public List<DataDictionaryType> findAll() {
        List<Map<String, String>> result = dataDictionaryTypeDao.findAll();
        ArrayList<DataDictionaryType> dataDictionaryTypes = new ArrayList<DataDictionaryType>();

        for (Map<String, String> entry : result) {
            DataDictionaryType type = new DataDictionaryType();
            type.setId(Integer.parseInt(entry.get("id")));
        }
        return dataDictionaryTypes;
    }
}
