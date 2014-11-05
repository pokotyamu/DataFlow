package DataMapper.one;

import DataFlow.DataSet;
import DataFlow.Pair;
import DataFlow.ProcessData;
import java.util.ArrayList;

public class CountYData extends One_DataMapper {

    @Override
    public DataSet map(DataSet ds) {
        DataSet temp = new DataSet(ds.getXname(),ds.getYname()+"_COUNT");

        for(int processindex = 0; processindex < ds.getProcessSize();processindex++){
            ProcessData tempprocess = new ProcessData(temp.getXname(),temp.getYname());
            ProcessData pd = ds.getProcessData(processindex).clone();
            ProcessData countpd = ds.getProcessData(processindex).getRealData().clone();
            int pairindex=0;

            while(pairindex < countpd.getRealSize()){
                Pair pair = new Pair(countpd.getPair(pairindex).getX(),0);
                for(int nextindex = pairindex+1;nextindex<countpd.getSize();nextindex++){
                    if(this.cheachPair(pair, countpd.getPair(nextindex))){
                        pair.setValue((int)pair.getY()+1);
                        pairindex = nextindex;
                    }else{                        
                        break;
                    }
                }
                tempprocess.addData(pair);
                pairindex++;
            }
            tempprocess.addData(pd.getPair("ST_ID"));
            tempprocess.addData(pd.getPair("Class_ID"));
            temp.addProcessData(tempprocess);
        }
        
        
        return temp;
    }

}