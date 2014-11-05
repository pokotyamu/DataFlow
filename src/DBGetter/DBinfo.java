package DBGetter;

public class DBinfo {
    private String table;
    private String keyName;
    private String value;
    
    public DBinfo(String table,String keyName,String value){
        this.table = table;
        this.keyName = keyName;
        this.value = value;
    }
    
    public String getSQL(){
        StringBuilder stb = new StringBuilder();
        stb.append("SELECT * FROM ");
        stb.append(table);
        return stb.toString();
    }
    
    public String getMaxSQL(){
        StringBuilder stb = new StringBuilder();
        stb.append("SELECT * FROM ");
        stb.append(table);
        stb.append(" X WHERE SUBMITION_ID=(SELECT MAX(SUBMITION_ID) FROM ");
        stb.append(table);
        stb.append(" WHERE ");
        stb.append(keyName);
        stb.append(" = X.");
        stb.append(keyName);
        stb.append(") ORDER BY ");
        stb.append(keyName);
        
        return stb.toString();
    }
    
    public String getKeyName(){
        return keyName;
    }
    
    public String getValue(){
        return value;
    }
}
