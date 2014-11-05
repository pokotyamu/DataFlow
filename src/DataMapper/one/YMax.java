package DataMapper.one;

import DataFlow.DataSet;
import DataFlow.Pair;
import DataFlow.ProcessData;

public class YMax extends One_DataMapper {

    @Override
    public DataSet map(DataSet ds) {
        if(ds.getProcessSize()>1){
            DataSet temp = new DataSet(ds.getXname(),ds.getYname()+"_MAX");
            ProcessData temppd = ds.getMaxProcessData().getRealData();            
            int processindex = 0;        
            ProcessData process = new ProcessData(ds.getXname(),temp.getYname());
            while(processindex<ds.getProcessSize()){
                ProcessData pd = ds.getProcessData(processindex).getRealData();
                for(int pairindex=0;pairindex<temppd.getSize();pairindex++){
                    Pair temppair = temppd.getPair(pairindex);
                    int nextprocessindex = processindex+1;
                    while(nextprocessindex<ds.getProcessSize()){
                        ProcessData nextpd = ds.getProcessData(nextprocessindex).getRealData();
                        for(int nextindex=pairindex;nextindex<temppd.getSize();nextindex++){ 
                            Pair nextpair = pd.getPair(nextindex);
                            if(cheachPair(temppair,nextpair)){
                                if((int)temppair.getY()>(int)nextpair.getY()){
                                    temppair = nextpair;
                                }
                            }
                        }
                        nextprocessindex++;
                    }
                    processindex = nextprocessindex;
                    process.addData(temppair);
                }
            }
            process.addData(new Pair("ST_ID",-1));
            temp.addProcessData(process);
            return temp;
        }else{
            
            return ds;
        }
    }
}
