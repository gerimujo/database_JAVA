package com.database.databsssee.Database.Resprository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.database.databsssee.Database.Model.tables;
import com.database.databsssee.Database.Model.tableunitcolums;

@Repository
public interface tableRep extends JpaRepository<tables, Integer> {

   List<tables> findByTablename(String tablename);
   List<tables> findByDatabaseid(Integer databaseid);
}
