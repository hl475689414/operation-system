package com.wmq.sys.entity;

public class CommonCity {
    private Integer id;

    private String name;

    private String fullname;

    public CommonCity(Integer id, String name, String fullname) {
        this.id = id;
        this.name = name;
        this.fullname = fullname;
    }

    public CommonCity() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname == null ? null : fullname.trim();
    }
}