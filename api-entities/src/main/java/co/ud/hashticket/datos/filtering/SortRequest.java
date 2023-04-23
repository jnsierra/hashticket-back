package co.ud.hashticket.datos.filtering;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SortRequest implements Serializable {
    private static final long serialVersionUID = 3194362295851723069L;
    private String key;
    private SortDirection direction;
}