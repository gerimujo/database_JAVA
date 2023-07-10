package com.database.databsssee.Database.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class tableunitcolums {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="type")
    private String type;
    @Column(name="length1")
    private Integer length;
    public String getTablename() {
        return tablename;
    }



    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    @Column(name="default1")
    private String default1;
    @Column(name="defaultValue")
    private String defaultValue;
    @Column(name="tableid")
    private Integer tableid;
    @Column(name="tablename")
    private String tablename;


   public tableunitcolums() {
       
    }


    
    public tableunitcolums(Integer id, String name, String type, Integer length, String default1, String defaultValue,
            Integer tableid,String tablename) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.length = length;
        this.default1 = default1;
        this.defaultValue = defaultValue;
        this.tableid = tableid;
        this.tablename =tablename;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getLength() {
        return length;
    }
    public void setLength(Integer length) {
        this.length = length;
    }
    public String getDefault1() {
        return default1;
    }
    public void setDefault1(String default1) {
        this.default1 = default1;
    }
    public String getDefaultValue() {
        return defaultValue;
    }
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    public Integer getTableid() {
        return tableid;
    }
    public void setTableid(Integer tableid) {
        this.tableid = tableid;
    }


    
 

    
  
}
