package neo.uap.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ResponseBody {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public ResponseBody(String error) {
        this.error = error;
    }

    public ResponseBody(Object data) {
        this.data = data;
    }
}
