package com.database.databsssee.Database.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;



@Entity
public class tablecolumnsinsert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tablename;
    public Integer getDatabaseid() {
        return databaseid;
    }
    
    public tablecolumnsinsert(Integer id, String tablename, Integer databaseid, String token,
            List<tableunitcolums> columns) {
        this.id = id;
        this.tablename = tablename;
        this.databaseid = databaseid;
        this.token = token;
        this.columns = columns;
    }

    public void setDatabaseid(Integer databaseid) {
        this.databaseid = databaseid;
    }
    private Integer databaseid;
   
    private String token;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<tableunitcolums> columns;
   
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTablename() {
        return tablename;
    }
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
    public List<tableunitcolums> getColumns() {
        return columns;
    }
    public void setColumns(List<tableunitcolums> columns) {
        this.columns = columns;
    }
    
}
