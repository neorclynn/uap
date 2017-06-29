package neo.uap.domain;

import lombok.Data;

@Data
public class Pageable {
    private int rowNum;
    private int offset;
    private int limit;
}
