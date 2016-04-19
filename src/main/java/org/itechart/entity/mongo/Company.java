package org.itechart.entity.mongo;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Company {
    @Id
    private Long id;

    private String name;

    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
