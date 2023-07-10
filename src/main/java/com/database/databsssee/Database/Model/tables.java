package com.database.databsssee.Database.Model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table
public class tables {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tableid")
    private Integer tableid;
    @Column(name="databaseid")
    private Integer databaseid;
    @Column(name="tablename")
    private String tablename;
    
   

    public tables(){
        
    }

    
    
 


    
    public tables(Integer tableid, Integer databaseid, String tablename) {
        this.tableid = tableid;
        this.databaseid = databaseid;
        this.tablename = tablename;
      
    }







    public Integer getDatabaseid() {
        return databaseid;
    }
    public void setDatabaseid(Integer databaseid) {
        this.databaseid = databaseid;
    }
    public Integer getTableid() {
        return tableid;
    }
    public void setTableid(Integer tableid) {
        this.tableid = tableid;
    }
    public String getTablename() {
        return tablename;
    }
    public void setTablename(String tablename) {
        this.tablename = tablename;
    }



    
}
