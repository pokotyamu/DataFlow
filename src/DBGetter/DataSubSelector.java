package DBGetter;

import DBCreater.DBController;
import DataFlow.DataSet;
import java.sql.ResultSet;

public class DataSubSelector extends DBSelector{
    
    private final DBController dbcon;
    
    public DataSubSelector(DBController dbcon){
        this.dbcon = dbcon;
    }

    @Override
    public DataSet crateDataSet(String xname, String yname, DBinfo dbinfo) {
        DataSet ds = new DataSet(xname,yname);
        ResultSet rs = dbcon.selectData(dbinfo.getMaxSQL());
        return this.makeData(xname, yname, dbinfo, rs);
    }
    
    


}
