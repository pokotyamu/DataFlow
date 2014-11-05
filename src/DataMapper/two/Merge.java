package DataMapper.two;

import DataFlow.DataSet;
import DataFlow.ProcessData;

public class Merge implements Two_DataMapper{


    @Override
    public DataSet map(DataSet data1, DataSet data2) {
        DataSet datas = new DataSet(data1);
        for(ProcessData data : data2.getProcessData()){
            datas.addProcessData(data);
        }
        return datas;
    }

}
