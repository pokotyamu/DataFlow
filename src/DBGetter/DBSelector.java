package DBGetter;

import DataFlow.DataSet;
import DataFlow.Pair;
import DataFlow.ProcessData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DBSelector {
    public abstract DataSet crateDataSet(String xname,String yname,DBinfo dbinfo);
    
    protected DataSet makeData(String xname, String yname, DBinfo dbinfo, ResultSet rs) {
        try{
            DataSet ds = new DataSet(xname,yname);
           
            while(rs.next()){
                int ST_ID = rs.getInt("ST_ID");
                int Class_ID = rs.getInt("Class_ID");
                //int SUBMITION = rs.getInt("SUBMITION_ID");
                boolean noHit = true;
                Pair p = new Pair(rs.getObject(dbinfo.getKeyName()),rs.getObject(dbinfo.getValue()));
                if(ds.getProcessSize()==0){
                    ProcessData newPd = new ProcessData(xname,yname);
                    newPd.addData(new Pair<>("ST_ID",ST_ID));
                    newPd.addData(new Pair<>("Class_ID",Class_ID));
                    newPd.addData(p);
                    ds.addProcessData(newPd);
                }else{
                    for(int index=0;index<ds.getProcessSize();index++){                    
                        ProcessData pd = ds.getProcessData(index).clone();
                        if(pd.getPair("ST_ID").getY().equals(ST_ID) && pd.getPair("Class_ID").getY().equals(Class_ID)){
                            pd.addData(p);
                            ds.setProcessData(index,pd);
                            noHit=false;
                        }else{
                            
                        }                
                    }
                    if(noHit){
                        ProcessData newPd = new ProcessData(xname,yname);
                        newPd.addData(new Pair<>("ST_ID",ST_ID));
                        newPd.addData(new Pair<>("Class_ID",Class_ID));
                        newPd.addData(p);
                        ds.addProcessData(newPd);
                    }
                }                
            }
            return ds;
        } catch (SQLException ex) {
            Logger.getLogger(DataCreater.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new DataSet(xname,yname);
    }
}
