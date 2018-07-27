package com.wmq.sys.entity;

public class Config {
    private Integer id;

    private String code;

    private String value;

    private Integer types;

    public Config(Integer id, String code, String value, Integer types) {
        this.id = id;
        this.code = code;
        this.value = value;
        this.types = types;
    }

    public Config() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }
}