package co.ud.hashticket.dto;

import lombok.Data;

@Data
public class JoinEntity {
    private String entity;
    private String value;
    private FieldType fieldType;
}
