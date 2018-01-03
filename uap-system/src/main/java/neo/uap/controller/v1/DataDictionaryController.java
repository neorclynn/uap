package neo.uap.controller.v1;

import com.fasterxml.jackson.databind.JsonNode;
import neo.uap.domain.common.ResponseBody;
import neo.uap.domain.system.DataDictionaryItem;
import neo.uap.domain.system.DataDictionaryType;
import neo.uap.service.DataDictionaryService;
import neo.uap.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/api/datadictionaries",
        produces = "application/vnd.datadictionaries-v1+json",
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class DataDictionaryController {
    @Autowired
    private DataDictionaryService dataDictionaryService;

    @RequestMapping(value = "/types", method = RequestMethod.GET)
    public ResponseBody listType(@RequestParam(value = "name", required = false) String name) {
        return new ResponseBody(dataDictionaryService.listType());
    }

    @RequestMapping(value = "/types/{id}", method = RequestMethod.GET)
    public ResponseBody getType(@PathVariable("id") Long id) {
        return new ResponseBody(dataDictionaryService.getType(id));
    }

    @RequestMapping(value = "/types/{id}/parent", method = RequestMethod.GET)
    public ResponseBody getParentType(@PathVariable("id") Long id) {
        return new ResponseBody(dataDictionaryService.getParentType(id));
    }

    @RequestMapping(value = "/types", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addType(@RequestBody DataDictionaryType dataDictionaryType) {
        dataDictionaryService.addType(dataDictionaryType);
    }

    @RequestMapping(value = "/types/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void editType(@PathVariable("id") Long id,
                         @RequestBody JsonNode body) {
        dataDictionaryService.editType(id, body);
    }

    @RequestMapping(value = "/items/{typeId}", method = RequestMethod.GET)
    public ResponseBody list(
            @PathVariable("typeId") Long typeId/*,
            @RequestParam(value = "typeName") String typeName,
            @RequestParam(value = "parentTypeName", required = false) String parentTypeName,
            @RequestParam(value = "parentValue", required = false) String parentValue*/) {
        /*if (StringUtils.isNotEmpty(parentTypeName) && StringUtils.isNotEmpty(parentValue)) {
            return ResponseEntity.ok(new ResponseBody<List<DataDictionaryItem>>(
                    dataDictionaryService.listItemByParentItem(typeName, parentTypeName, parentValue)));
        }*/

        return new ResponseBody(dataDictionaryService.listItemByTypeId(typeId));
    }

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@RequestBody DataDictionaryItem dataDictionaryItem) {
        dataDictionaryItem.setCreatedBy(UserUtil.getCurrentUsername());
        dataDictionaryService.addItem(dataDictionaryItem);
    }

    @RequestMapping(value = "/items/{itemId}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public void edit(@PathVariable("itemId") Long itemId,
                     @RequestBody DataDictionaryItem dataDictionaryItem) {
        dataDictionaryItem.setId(itemId);
        dataDictionaryItem.setUpdatedBy(UserUtil.getCurrentUsername());
        dataDictionaryService.editItem(dataDictionaryItem);
    }
}
