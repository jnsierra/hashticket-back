package co.ud.ud.hashticket.dto.responses;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class GenericQuery <T> {

    private Set<T> results;
    private Integer page;
    private Integer records;
    private Integer totalRecords;
}
