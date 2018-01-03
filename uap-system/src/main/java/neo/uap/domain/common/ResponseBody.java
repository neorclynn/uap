package neo.uap.domain.common;

import lombok.Data;

@Data
public class ResponseBody {
    private Integer code;

    private String message;

    private Object data;

    public ResponseBody() {

    }

    public ResponseBody(String message) {
        this.message = message;
    }

    public ResponseBody(Object data) {
        this.data = data;
    }
}
