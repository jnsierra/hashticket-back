package co.ud.hashticket.datos.filtering;

import lombok.Data;

@Data
public class JoinEntity {
    private String entity;
    private String value;
    private FieldType fieldType;
}