package com.database.databsssee.Database.Resprository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.database.databsssee.Database.Model.databasesdata;

@Repository
public interface databasedataRep extends JpaRepository<databasesdata,Integer> {
    
}
