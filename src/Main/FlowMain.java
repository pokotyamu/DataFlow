package Main;


import DBCreater.DBController;
import DBCreater.FileList;
import DBGetter.DBSelector;
import DBGetter.DBinfo;
import DBGetter.DataSelector;
import DBGetter.DataSubSelector;
import DataFlow.*;
import DataMapper.one.AveYData;
import DataMapper.one.CountYData;
import DataMapper.one.YMax;
import DataMapper.one.YMin;
import java.io.File;
import java.util.ArrayList;


public class FlowMain {
    

    public static void main(String[] args) {
        
        FileList fl = new FileList("/Users/pokotyamu/Desktop/Kyutech");
        

        
        
//        System.out.println(TestMain.class.getResource("TestMain.class"));
//FileList fc = new FileList("");
        DBController dbcon = new DBController();
        for(File f : fl.getFiles()){
            dbcon.setDB(f);

            dbcon.createAllTable();
            dbcon.registallDB(dbcon.getST_ID(f), dbcon.getClass_ID(f),dbcon.getSUBMITION(f));
        }
        
        
        
        /*
        
        
        
        DBinfo dbinfo = new DBinfo("LOGDDETAIL","PROJECTID","DESCRIPTION");
        DBinfo dbinfo2 = new DBinfo("PROGRAMSIZE","PROJECTID","ACTUALT");
        DBController dbcon = new DBController();

        ArrayList<DBSelector> selectors = new ArrayList();
        selectors.add(new DataSelector(dbcon));
        selectors.add(new DataSelector(dbcon));
        selectors.add(new DataSubSelector(dbcon));
                
        DataSet ds = selectors.get(0).crateDataSet("課題番号", "欠陥番号", dbinfo);
        DataSet ds2 = selectors.get(1).crateDataSet("課題番号", "提出回数", dbinfo);
        DataSet ds3 = selectors.get(2).crateDataSet("課題番号", "LOC", dbinfo2);
        AveYData ave = new AveYData();
        CountYData coy = new CountYData();
        YMax y = new YMax();
        YMin ym = new YMin();
        DataSet map = ave.map(ds3);

        FlowMain.print(ds);
        FlowMain.print(ds2);
        FlowMain.print(ds3);        
        FlowMain.print(map);
        DataSet cmap = coy.map(ds);        
        DataSet p = y.map(cmap);
        DataSet p1 = ym.map(cmap);        
        FlowMain.print(cmap);
        FlowMain.print(p);
        FlowMain.print(p1);
                
                */
        
    }

    private static void print(DataSet ds) {
  
        for(int i=0;i < ds.getProcessSize();i++){
            ProcessData pd = ds.getProcessData(i);
            Pair pa = pd.getPair("ST_ID");
            System.out.println("["+pd.getXname()+","+pd.getYname()+"]");
            System.out.println("["+pa.getX()+","+pa.getY()+"]");
            for(int k=0;k < pd.getSize();k++){
                Pair pair = pd.getPair(k);
                if(!pair.isSymbol()){
                    System.out.println("["+pair.getX()+","+pair.getY()+"]");
                }
            }
        }
            
    }
}
    


