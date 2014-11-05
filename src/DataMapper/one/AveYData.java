package DataMapper.one;

import DataFlow.DataSet;
import DataFlow.Pair;
import DataFlow.ProcessData;
import java.util.ArrayList;

public class AveYData extends One_DataMapper{

    @Override
    public DataSet map(DataSet ds) {
        DataSet temp = new DataSet(ds.getXname(),ds.getYname());
        ProcessData aveData = new ProcessData(temp.getXname(),temp.getYname()+"_AVE");
        ProcessData pdata = ds.getMaxProcessData().getRealData();
        for(Pair p : (ArrayList<Pair>)pdata.getRealData().getData()){
             System.out.println("["+p.getX()+","+p.getY()+"]");
        }
        
        for(int pairindex=0;pairindex< pdata.getRealSize();pairindex++){
            double aved = 0.0;
            for(int processindex = 0;processindex < ds.getProcessSize();processindex++){
                if(this.cheachPair(pdata.getPair(pairindex), ds.getProcessData(processindex).getRealData().getPair(pairindex))){
                    aved += new Double(pdata.getPair(pairindex).getY().toString());    
                }
            }
            aved = aved/ds.getProcessSize();
            aveData.addData(new Pair(ds.getProcessData(0).getRealData().getPair(pairindex).getX(),aved));
        }
        aveData.addData(new Pair("ST_ID",-1));
        aveData.addData(ds.getMaxProcessData().getPair("Class_ID"));
        temp.addProcessData(aveData);
        return temp;
    }

}
