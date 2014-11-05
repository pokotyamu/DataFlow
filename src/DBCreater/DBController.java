/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCreater;


import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.RowId;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pokotyamu
 */
public class DBController {
    Connection con;
    Database db;
    private PreparedStatement psCreate;
    //コンストラクタでDBに接続
    public DBController() {
        try {
            String url= "jdbc:derby://localhost/PSP_for_E";
            String usr = "root";
            con = DriverManager.getConnection(url,usr,usr);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registallDB(int ST_ID, int ClassID,int SUBMITION){
        try {
            Object[] tablenames = db.getTableNames().toArray();
            for(Object tablename: tablenames){
                List<? extends Column> columns = db.getTable((String)tablename).getColumns();
                for(Row row : db.getTable((String)tablename)){
                    String sql = "INSERT INTO "+tablename+ " values("+ST_ID+","+ClassID;
                    String selectsql ="SELECT COUNT(0) FROM "+tablename+" WHERE("+tablename+".ST_ID = "+ST_ID+" AND "+tablename+".CLASS_ID = "+ClassID;
                    
                    Collection<Object> vs = row.values();
                    RowId id = row.getId();
                    //System.out.println(vs.toString());
                    int index = 0;
                    for(Object v : vs){
                        Column c = (Column)columns.get(index);
                        String tempsql = ""+ this.enValue(this.enJDBC((c).getType()),v);
                        sql+=","+ tempsql;
                        if(tempsql.equals("null")){
                            selectsql += " AND " +tablename+"."+this.enColumName(c.getName()) + " IS "+ "NULL";
                        }else{
                            selectsql += " AND " +tablename+"."+this.enColumName(c.getName()) + " = "+tempsql;
                        }
                        index++;
                    }
                    this.insertTable(sql+","+SUBMITION+")",selectsql+")");
                } 
            }
        } catch (IOException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void insertTable(String sql,String selectsql) {
        try {
            ResultSet rs = this.selectData(selectsql);
            while(rs.next()){
                if(rs.getInt("1") == 0){
                    Statement stmt = con.createStatement();
                    int num = stmt.executeUpdate(sql);
                    stmt.close();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }          
    }
    
    public void createAllTable(){
        try {
            Object[] tablenames = db.getTableNames().toArray();
            for(Object tablename :tablenames){
                String sql = "CREATE TABLE ";
                sql += tablename + "(ST_ID INTEGER,CLASS_ID INTEGER";
                List<? extends Column> columns = db.getTable((String)tablename).getColumns();
                for(Column colum :columns){
                    sql+=","+this.enColumName(colum.getName())+" "+ this.enJDBC(colum.getType());
                }
                createTable(sql+",SUBMITION_ID INTEGER)");
            }
        } catch (IOException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void createTable(String sql) throws SQLException {
        try {
            try (Statement stmt = con.createStatement()) {
                stmt.execute(sql);
                stmt.close();
            }
            
        } catch (SQLException ex) {
            if(!ex.getSQLState().equals("X0Y32")) {
                throw ex;
            }
        }
    }

    private String enJDBC(DataType type) {
        switch(type.toString()){
            case "LONG":
                return "INTEGER";
            case "TEXT":
                return "VARCHAR(500)";
            case"SHORT_DATE_TIME":
                return "TIMESTAMP";
            case"MEMO":
                    return "VARCHAR(500)";
        }
        return type.toString();
    }

    private String enColumName(String name) {
        switch(name){
            case "AT":
                return "MYAT";
        }
        return name;
    }

    private Object enValue(String type, Object v) {
        if(v != null){
            switch(type.toString()){
                case "VARCHAR(500)":
                    String str = v.toString();
                    str = str.replace(",", "-");
                    str = str.replace(" ", "");
                    return "'"+str+"'";
                case "TIMESTAMP":
                    return this.timeChange(v.toString());
            }
            return v;
        }else{
            return null;
        }
    }

    private String timeChange(String str) {
        Timestamp time2 = null;
        try {
            String TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
            String spl[] = str.split(" ");
            SimpleDateFormat sdf1 = new SimpleDateFormat(TIME_FORMAT);
            time2 = new Timestamp(sdf1.parse(spl[5]+"/"+this.month(spl[1])+"/"+spl[2]+" " +spl[3]).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "'"+time2.toString()+"'";
    }

    private int month(String month) {
        switch(month){
            case"Jan":
                return 1;
            case"Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
        }
        return 1;
    }

    public ResultSet selectData(String sql) {
        System.out.println(sql);
        try {
            PreparedStatement ps= con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();            
            
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setDB(File f){
        try {
            db = DatabaseBuilder.open(f);
        } catch (IOException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }

    public int getST_ID(File f) {
        String sqstr = f.getParent();
        String str[] = sqstr.split("/");
        String spstr[] = str[str.length-2].split("-");
        String temp = spstr[0];
        return Integer.parseInt(temp); 
    }

    public int getClass_ID(File f) {
        String sqstr = f.getParent();
        String str[] = sqstr.split("/");
        return Integer.parseInt(str[str.length-3]);
    }
    
    public int getSUBMITION(File f) {
        String sqstr = f.getParent();
        String str[] = sqstr.split("/");
        String spstr[] = str[str.length-1].split("-");
        String temp = spstr[1];
        return Integer.parseInt(temp);
    }
    
    public void allTable() {
        try {
            Object[] tablenames = db.getTableNames().toArray();
            for(Object tablename :tablenames){
                System.out.println(tablename);
            }
        } catch (IOException ex) {
            Logger.getLogger(DBController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

