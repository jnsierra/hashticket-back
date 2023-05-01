package co.ud.hashticket.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class JoinEntity implements Serializable {

    private static final long serialVersionUID = 851462583201979138L;
    private String entity;
    private String value;
    private FieldType fieldType;
}
