package uap.dao;

import java.util.List;
import java.util.Map;

public interface DataDictionaryTypeDao {
    List<Map<String, String>> findAll();
}
