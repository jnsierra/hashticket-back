package co.ud.hashticket.dto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Operator {
    EQUAL ,
    EQUAL_EMBEDDED_ID,
    NOT_EQUAL,
    LIKE,
    IN,
    BETWEEN
}
