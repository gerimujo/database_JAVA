package com.database.databsssee.Database.Model;

import java.util.List;

public class databasenavbar {
    

    private Integer id;
    private String name;
    private List<tables> table;

    



    public databasenavbar(Integer id, String name, List<tables> table) {
        this.id = id;
        this.name = name;
        this.table = table;
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
        this.name = name;
    }
    public List<tables> getTable() {
        return table;
    }
    public void setTable(List<tables> table) {
        this.table = table;
    }





    
}
