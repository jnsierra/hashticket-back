package co.ud.hashticket.datos.filtering;

import lombok.Data;

import java.io.Serializable;

@Data
public class JoinEntity implements Serializable {

    private static final long serialVersionUID = 851462320197214838L;
    private String entity;
    private String value;
    private FieldType fieldType;
}
