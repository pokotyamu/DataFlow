package DBGetter;

import DBCreater.DBController;
import DataFlow.DataSet;
import java.sql.ResultSet;

public class DataSelector extends DBSelector{
    private DBController dbcon;

    public DataSelector(DBController dbcon){
        this.dbcon = dbcon;
    }
    @Override
    public DataSet crateDataSet(String xname, String yname, DBinfo dbinfo) {
        DataSet ds = new DataSet(xname,yname);
        ResultSet rs = dbcon.selectData(dbinfo.getSQL());
        return this.makeData(xname, yname, dbinfo, rs);
    }

}
