package com.database.databsssee.Database.Resprository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.database.databsssee.Database.Model.datatable;

public interface datatableRep extends JpaRepository<datatable, Integer> {
   @Query("SELECT DISTINCT d.datarowid FROM datatable d WHERE d.datacolumnid = :id")
    List<Integer> findDistinctDatarowIdsByDatacolumnId(@Param("id") Integer datacolumnid);

    List<datatable> findByDatarowid(Integer datarowid);
    List<datatable> findByDatacolumnid(Integer datacolumnid);
}
