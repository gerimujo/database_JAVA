package com.database.databsssee.Database.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class datatable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dataid")
    private Integer dataid;

    @Column(name = "datacolumnid")
    private Integer datacolumnid;

    @Column(name = "datavalue")
    private String datavalue;

    @Column(name = "dataType")
    private String dataType;

    @Column(name = "datarowid")
    private Integer datarowid;
    @Column(name="tablecolumdidreal")
    private Integer tablecolumdidreal;


    public datatable() {
    }

  



    public datatable(Integer dataid, Integer datacolumnid, String datavalue, String dataType, Integer datarowid,
            Integer tablecolumdidreal) {
        this.dataid = dataid;
        this.datacolumnid = datacolumnid;
        this.datavalue = datavalue;
        this.dataType = dataType;
        this.datarowid = datarowid;
        this.tablecolumdidreal = tablecolumdidreal;
    }





    public Integer getDataid() {
        return dataid;
    }

    public void setDataid(Integer dataid) {
        this.dataid = dataid;
    }

    public Integer getDatacolumnid() {
        return datacolumnid;
    }

    public void setDatacolumnid(Integer datacolumnid) {
        this.datacolumnid = datacolumnid;
    }

    public String getDatavalue() {
        return datavalue;
    }

    public void setDatavalue(String datavalue) {
        this.datavalue = datavalue;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getDatarowid() {
        return datarowid;
    }

    public void setDatarowid(Integer datarowid) {
        this.datarowid = datarowid;
    }





    public Integer getTablecolumdidreal() {
        return tablecolumdidreal;
    }





    public void setTablecolumdidreal(Integer tablecolumdidreal) {
        this.tablecolumdidreal = tablecolumdidreal;
    }
}
