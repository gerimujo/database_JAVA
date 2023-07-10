package com.database.databsssee.Database.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.database.databsssee.Database.Model.databasesdata;
import com.database.databsssee.Database.Model.tablecolumnsinsert;
import com.database.databsssee.Database.Model.tables;
import com.database.databsssee.Database.Services.Services;
import com.database.databsssee.config.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class controller {

@Autowired
private Services services;

@Autowired
private TokenUtil tokenUtil;


@GetMapping(value="/gettoken")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> getToken(){
    return ResponseEntity.ok(tokenUtil.generateTokenadmin());
  
}


    @PostMapping(value="/addDatabse")
    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    public  ResponseEntity<String> addDatbase(@RequestBody databasesdata datatoadd){
        try{
      return  services.addDatabse(datatoadd);

        }catch(Exception e){
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
        }

    }
   @PostMapping(value="/inserttablecolumns")
 //  @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> insertTableNameAndColumns(@RequestBody tablecolumnsinsert data){

    try{
return services.insertcolumns(data);
    }catch(Exception e){
         return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done"); 
    }


}

       @PostMapping(value="/inserttablecolumns1")
  @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> insertTableNameAndColumns1(@RequestBody tablecolumnsinsert data){

    try{
return services.insertcolumns(data);

    }catch(Exception e){
         return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done"); 
    }

 
}

 @PostMapping(value="/getcolumns")
 @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> MerrColumnsTable(@RequestBody List<String> data){
try{
return services.MerrColumnsTable(data);

}catch(Exception e){
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done"); 

}

}
 @PostMapping(value="/getcolumns1")
// @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> MerrColumnsTable1(@RequestBody List<String> data){
try{
return services.MerrColumnsTable(data);

}catch(Exception e){
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done"); 

}

}
@PostMapping(value="inserttotabel")
 @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> insert1(@RequestHeader("Authorization") String token,@RequestBody String sql){
    try{
         List<String> dat= new ArrayList<>();
        dat.add(token);
        dat.add(sql);
       return services.insetData(dat);
       // ObjectMapper obj = new ObjectMapper();
     

    }catch(Exception e){
                     return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done"); 


    }

}

@GetMapping(value="getdatabaseAndTable")
 @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> getdatabaseAndTable(){


    try{
     return    services.getdatabaseAndTable();

    }catch(Exception e){
                             return  ResponseEntity.status(400).body("Not done"); 

    }
}
@GetMapping(value="GetTableForDatabase/{id}")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> GetTableForDatabase(@RequestHeader("Authorization") String token,@PathVariable("id") Integer id){


    try{
        if(tokenUtil.getRoleFromToken(token).equals("admin")){
 return    services.GetTableForDatabase(id);
        }else{
                                         return  ResponseEntity.status(400).body("Not done"); 

        }
    

    }catch(Exception e){
                             return  ResponseEntity.status(400).body("Not done"); 

    }
}
@GetMapping(value = "getdatabeses11")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public ResponseEntity<String> getdatabses(@RequestHeader("Authorization") String token){
try{
    if(tokenUtil.getRoleFromToken(token).equals("admin")){
return services.getDatabases();
    }else{
                                        return  ResponseEntity.status(400).body("Not done"); 
 
    }

}catch(Exception e){
                                 return  ResponseEntity.status(400).body("Not done"); 

}




}



}
