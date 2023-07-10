package com.database.databsssee.Database.Resprository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.database.databsssee.Database.Model.tableunitcolums;

@Repository
public interface tableColumsRep extends JpaRepository<tableunitcolums,Integer>{
    

 @Query("SELECT o FROM tableunitcolums o WHERE o.tableid = :tableId")
    List<tableunitcolums> findByTableId(@Param("tableId") Integer tableId);
    
    @Query("SELECT o FROM tableunitcolums o WHERE o.name = :name")
     List<tableunitcolums> findByColumnName(@Param("name") String name);}
