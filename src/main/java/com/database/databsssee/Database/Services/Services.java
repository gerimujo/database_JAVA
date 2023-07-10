package com.database.databsssee.Database.Services;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.mapping.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.database.databsssee.Database.Model.databasenavbar;
import com.database.databsssee.Database.Model.databasesdata;
import com.database.databsssee.Database.Model.datatable;
import com.database.databsssee.Database.Model.tablecolumnsinsert;
import com.database.databsssee.Database.Model.tables;
import com.database.databsssee.Database.Model.tableunitcolums;
import com.database.databsssee.Database.Resprository.databasedataRep;
import com.database.databsssee.Database.Resprository.datatableRep;
import com.database.databsssee.Database.Resprository.tableColumsRep;
import com.database.databsssee.Database.Resprository.tableRep;
import com.database.databsssee.config.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class Services {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private databasedataRep databasedataRep;

    @Autowired
    private tableColumsRep tableColumsRep;

    @Autowired
    private tableRep tableRep;

    @Autowired
    private datatableRep datatableRep;

    public ResponseEntity<String> addDatabse(databasesdata datatoadd){
        
      try{
        if(tokenUtil.getRoleFromToken(datatoadd.getToken()).equals("admin")){
 databasedataRep.save(datatoadd);
          return ResponseEntity.ok(datatoadd.getId().toString());
        }else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

        }
         
      }catch(Exception e){
return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(datatoadd.toString());
      }

    }

    public ResponseEntity<String>  insertcolumns(tablecolumnsinsert data){
    try{
if(tokenUtil.getRoleFromToken(data.getToken()).equals("admin")){
    tables tab1 = new tables( null,data.getDatabaseid(), data.getTablename());
   
     tableRep.save(tab1);
     List<tables> tab2 = tableRep.findByTablename(data.getTablename());
     List<tableunitcolums> columns =  data.getColumns();
     for(int i =0; i<columns.size(); i++){
      columns.get(i).setTableid(tab2.get(0).getTableid());
      columns.get(i).setTablename(data.getTablename());
tableColumsRep.save(columns.get(i));
     }
       
      
     
        return ResponseEntity.ok(  tab1.getTableid().toString());
}else{
 
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not done");

}
      
      }catch(Exception e){
      
      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());

      }
    } 

    public ResponseEntity<String> MerrColumnsTable(List<String> data){

      try{
if(tokenUtil.getRoleFromToken(data.get(0)).equals("admin")){
List<tableunitcolums> ss = tableColumsRep.findByTableId(Integer.parseInt(data.get(1)));
 List<Integer> datarowid= datatableRep.findDistinctDatarowIdsByDatacolumnId(Integer.parseInt(data.get(1)));
 List<List>response= new ArrayList<>();
 List<List> response2= new ArrayList<>();

 for(int i = 0; i<datarowid.size();i++){
  List<datatable> dttable = datatableRep.findByDatarowid(datarowid.get(i));
  List<String> dt2 = new ArrayList<>();

  for(int j =0;j< dttable.size();j++){
    dt2.add(dttable.get(j).getDatavalue());
  }
response.add(dt2);



}
response2.add(ss);
response2.add(response);

ObjectMapper maper =  new ObjectMapper();
String reponse =  maper.writeValueAsString(response2);
return ResponseEntity.ok(reponse);
}else{
   return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");
}

      }catch(Exception e){
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());

      }
    }public ResponseEntity<String> insetData(List<String> data){
try{
  //tokenUtil.getRoleFromToken(data.get(0)).equals("admin")
  if(tokenUtil.getRoleFromToken(data.get(0)).equals("admin")){
    Random random = new Random();
    Integer randomNumber = random.nextInt(100000);
    String[] arrayofwords = data.get(1).split(" ");
    String tablename="";
    Integer tableid =0;
    String   delteintems="";

      String lengthDeriToDataToInsert1 ="";
List<String> datatoinsert = new ArrayList<>();
    if(arrayofwords.length>0& arrayofwords[0].equals("INSERT")){
      if(arrayofwords.length>1&&arrayofwords[1].equals("INTO")){
        if(arrayofwords.length>2){
          tablename = arrayofwords[2];
          List<tables> tab  = tableRep.findByTablename(tablename);
        
          if(tab.size()!=0){
            tableid = tab.get(0).getTableid();
            List<tableunitcolums> colum = tableColumsRep.findByTableId(tableid);
            if(arrayofwords.length>3&&arrayofwords[3].equals("VALUES")){
if(arrayofwords.length>4&&arrayofwords[4].contains("(")){
  if(arrayofwords[arrayofwords.length-1].contains(")")){
    Integer lengthDeriToDataToInsert = arrayofwords[0].length() +  arrayofwords[1].length()+arrayofwords[2].length()+arrayofwords[3].length()+4;
  lengthDeriToDataToInsert1 =data.get(1).substring(lengthDeriToDataToInsert+1, data.get(1).length()-1);
String[] datacell =  lengthDeriToDataToInsert1.split(",");
for(int i =0; i<datacell.length;i++){
  datacell[i]=datacell[i].trim();
}

for(int i = 0; i<datacell.length; i++){
  datatoinsert.add(datacell[i]);
}
if(datatoinsert.size()==colum.size()){
  for(int i =0;i<datatoinsert.size(); i++){
if(colum.get(i).getType().equals("VARCHAR")){
  if((datatoinsert.get(i).startsWith("'")&&datatoinsert.get(i).endsWith("'"))||(datatoinsert.get(i).equals("Null")||datatoinsert.get(i).equals("NULL"))){
    if(datatoinsert.get(i).length()<=colum.get(i).getLength()){

    if(datatoinsert.get(i).equals("Null")||datatoinsert.get(i).equals("NULL")){
        if(colum.get(i).getDefault1().equals("Null")){

        }else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The column "+colum.get(i).getName()+" doesn't accept null values");

        }
      }else{
        datatoinsert.set(i, datatoinsert.get(i).substring(1,  datatoinsert.get(i).length()-1));
      }

    }else{
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The data "+datatoinsert.get(i)+" is longer then it should be  ");

    }

  }else{
     return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete with the right data type in "+datatoinsert.get(i));

  }
}

if(colum.get(i).getType().equals("INT")){
  boolean ft = false;
  int prov =-999;
  try{
    prov =Integer.parseInt(datatoinsert.get(i));
if(prov==Integer.parseInt(datatoinsert.get(i))){
  ft = true;
}


  }catch(Exception e){
   return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete with the right data type in "+datatoinsert.get(i));
 

  }
  if(ft||(datatoinsert.get(i).equals("Null")||datatoinsert.get(i).equals("NULL"))){
    if(datatoinsert.get(i).length()<=colum.get(i).getLength()){
  

    if(datatoinsert.get(i).equals("Null")||datatoinsert.get(i).equals("NULL")){
        if(colum.get(i).getDefault1().equals("Null")){

        }else{
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The column "+colum.get(i).getName()+" doesn't accept null values");

        }
      }

    }else{
           return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The data "+datatoinsert.get(i)+" is longer then it should be  ");

    }

  }else{
     return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete with the right data type in "+datatoinsert.get(i));

  }
}





  }

}else{
 return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please match the number of data with columns number");

}
     for(int i =0;i<datatoinsert.size(); i++){
datatable dat =  new datatable();
dat.setDatarowid(randomNumber);
dat.setDataType(colum.get(i).getType());
dat.setDatacolumnid(colum.get(i).getTableid());
dat.setDatavalue(datatoinsert.get(i));
dat.setTablecolumdidreal(colum.get(i).getId());
datatableRep.save(dat);

     }
        return ResponseEntity.ok("Insert is  done ");

  }else{
      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sytax error near "+arrayofwords[arrayofwords.length-1]);

  }

}else{
   return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sytax error near "+arrayofwords[4]);

}
            }else{
          return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Sytax error near "+arrayofwords[3]);

            }

          }else{
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete the QUERY with the correct table name");

          }


        }else{
                    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete the QUERY with the table name");

        }


      }else{
          return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is an error near the "+arrayofwords[1]);

      }

    } 
    if(arrayofwords.length!=0&&arrayofwords[0].equals("UPDATE")) {
      if(arrayofwords.length>1){
 if(arrayofwords.length>1){
          
          tablename = arrayofwords[1];
          List<tables> tab  = tableRep.findByTablename(tablename);
        
          if(tab.size()!=0){
if(arrayofwords.length>2&&arrayofwords[2].equals("SET")){
List<tableunitcolums> col1 = tableColumsRep.findByColumnName(arrayofwords[3]);
if(col1.size()!=0){

  if(col1.get(0).getType().equals("INT")){
    try{
      Integer id= Integer.parseInt(arrayofwords[5]);
    }catch(Exception e){
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The data type of the column you want ot update is not correct");

    }
  }
  if(col1.get(0).getType().equals("VARCHAR")){
    if(arrayofwords[5].startsWith("'")&&arrayofwords[9].endsWith("'")){
 arrayofwords[5] = arrayofwords[5].substring(1,arrayofwords[5].length()-1);
    }else{
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The data type of the column you want ot update is not correct");
    }

  }


  if(arrayofwords[6].equals("WHERE")){

  }else{
          return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete with the right syntax near "+arrayofwords[6]);

  }


  List<tableunitcolums> col2 = tableColumsRep.findByColumnName(arrayofwords[7]);
if(col2.size()!=0){

  if(col2.get(0).getType().equals("INT")){
    try{
      Integer id= Integer.parseInt(arrayofwords[9]);
    }catch(Exception e){
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The data type of the condition column is not correct2");

    }
  }
  String uube ="jo";
  if(col2.get(0).getType().equals("VARCHAR")){
    if(arrayofwords[9].startsWith("'")&&arrayofwords[9].endsWith("'")){
 arrayofwords[9] =  arrayofwords[9].substring(1,arrayofwords[9].length()-1);


    }else{
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The data type of the condition column is not correct3");

  }
  }
  ObjectMapper obj = new ObjectMapper();
 
  List<datatable> rowsdata= datatableRep.findByDatacolumnid(col2.get(0).getTableid());
  for(int i=0;i<rowsdata.size();i++){
    if(rowsdata.get(i).getDatavalue().equals(arrayofwords[9])){
   
      List<datatable> rows1 = datatableRep.findByDatarowid(rowsdata.get(i).getDatarowid());
      uube = obj.writeValueAsString(rows1  );
      uube = uube + obj.writeValueAsString(col1);
      for(int j=0;j<rows1.size();j++){
        if(rows1.get(j).getTablecolumdidreal().toString().equals(col1.get(0).getId().toString())){
rows1.get(j).setDatavalue(arrayofwords[5]);
datatableRep.save(rows1.get(j));
   uube="po1";
        }
      }
    }
  }

  
return ResponseEntity.ok( "Update is done" );

}else{
      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The columun you want to update doesn't exist  "+arrayofwords[9]);}

}else{
      return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The columun you want to update doesn't exist");

}
}else{
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a syntax error near "+arrayofwords[2]);

}

          }}
      }else{
                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete the query");

      }
    }
    
    if(arrayofwords.length!=0&& arrayofwords[0].equals("DELETE")){

      if(arrayofwords.length>0&& arrayofwords[1].equals("FROM")){
          if(arrayofwords.length>2){
          
          tablename = arrayofwords[2];
          List<tables> tab  = tableRep.findByTablename(tablename);
        
          if(tab.size()!=0){

            tableid = tab.get(0).getTableid();
            List<tableunitcolums> colum = tableColumsRep.findByTableId(tableid);
            List<datatable> rowsdata = datatableRep.findByDatacolumnid(tableid);

            if(arrayofwords.length>3&& arrayofwords[3].equals("WHERE")){

       delteintems=data.get(1).substring( arrayofwords[0].length()+ arrayofwords[1].length()+arrayofwords[2].length()+arrayofwords[3].length() +4,data.get(1).length());

       String[] arraytodelete = delteintems.split(" ");
       for(int i =0;i<arraytodelete.length;i++){
        arraytodelete[i].trim();
       }
       if(arraytodelete.length==3){
List<tableunitcolums> col1 = tableColumsRep.findByColumnName(arraytodelete[0]);
if(col1.size()!=0){
  String type = col1.get(0).getType();
  if(type.equals("INT")){
try{
  Integer e = Integer.parseInt(arraytodelete[2]);

}catch(Exception e){
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete with the right data type in"+arrayofwords[3]);

}
  }

  if(type.equals("VARCHAR")){
    if(arraytodelete[2].startsWith( "'")&&(arraytodelete[2].endsWith("'"))){
arraytodelete[2].substring(1,arraytodelete[2].length()+1);
    }else{
         return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete with the right data type in"+arrayofwords[3]);
 
    }
  }


  for(int i =0;i<rowsdata.size();i++){
    if(rowsdata.get(i).getDatavalue().equals(arraytodelete[2])){
      List<datatable> rows = datatableRep.findByDatarowid(rowsdata.get(i).getDatarowid());
      for(int j =0; j<rows.size();j++){
        datatableRep.deleteById(rows.get(j).getDataid());
      }
    }

  }
 return ResponseEntity.ok("Delete was done");
  

}else{
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The column doesn't exist");

}




       }else{
        ObjectMapper obk =  new ObjectMapper();
        return ResponseEntity.status(400).body(obk.writeValueAsString(arraytodelete));
         //   return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete with the right conditions");

       }

            }else{
                     return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete with  the right syntax near "+arrayofwords[3]);

            }
          
          
          }else{
       return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete with  the right table name");

            }
          }else{
         return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Please complete the QUERY");

          }

      }else{
             return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is an error near "+arrayofwords[1]);

      }

    }  

    
    
    else {
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is an error near "+arrayofwords[0]);

    }
  
  
//ObjectMapper obj = new ObjectMapper();
//if(arrayofwords.length>0&& arrayofwords[0].equals("DELETE"))
//String delteintems = data.get(1).substrisng(0, 7);
//Integer xx = arrayofwords.length;
  // return ResponseEntity.ok(obj.writeValueAsString(arrayofwords));
        ///       return  ResponseEntity.ok(delteintems);
  
   }
  
   else{
   return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not done");

  }  

}catch(Exception e){

                return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());

}

    }public ResponseEntity<String> getdatabaseAndTable(){
      try{
List<databasenavbar> response = new ArrayList();
        List<databasesdata> datbas = databasedataRep.findAll();
        for(int i=0;i<datbas.size();i++){
          List<tables> tab = tableRep.findByDatabaseid(datbas.get(i).getId());
          databasenavbar dt = new databasenavbar(datbas.get(i).getId(), datbas.get(i).getName(), tab);
          response.add(dt);
        }
ObjectMapper obj = new ObjectMapper();

return ResponseEntity.ok(obj.writeValueAsString(response));


      }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

      }
    }
    
    public ResponseEntity<String> GetTableForDatabase(Integer id){
      try{
        List<List> response= new ArrayList<>();
List<tables> tab= tableRep.findByDatabaseid(id);
List<Integer> xx = new ArrayList<>();
for(int i  = 0; i<tab.size();  i++){
  List<Integer> dt1 = datatableRep.findDistinctDatarowIdsByDatacolumnId(tab.get(i).getTableid());
xx.add(dt1.size());
 

}
response.add(tab);
response.add(xx);

ObjectMapper obj = new ObjectMapper();

return ResponseEntity.ok(obj.writeValueAsString(response));


      }catch(Exception e){
                        return  ResponseEntity.status(400).body(e.toString());

      }
    }
    public ResponseEntity<String> getDatabases(){
      try{
        List<databasesdata> dat1 = databasedataRep.findAll();
ObjectMapper obj = new ObjectMapper();
return ResponseEntity.ok(obj.writeValueAsString(dat1));
      }catch(Exception e){
                                return  ResponseEntity.status(400).body(e.toString());

      }
    }
}
