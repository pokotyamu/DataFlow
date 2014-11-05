
package DataFlow;

import java.util.ArrayList;

public class DataSet {
    
    private ArrayList<ProcessData> processdata;
    private String xname;
    private String yname;
    
    public DataSet(String xname,String yname){
        this.processdata = new ArrayList();
        this.xname = xname;
        this.yname = yname;
    }

    public DataSet(DataSet datas) {
       this.processdata = datas.getProcessData();
    }
    
    public void addProcessData(ProcessData data){
        this.processdata.add(data);
    }    
    
    public ArrayList<ProcessData> getProcessData(){
        ArrayList<ProcessData> temp = new ArrayList();
        for(ProcessData pdata : processdata){
            temp.add(pdata);
        }
        return temp;        
    }
    
    public ProcessData getProcessData(int index){
        return processdata.get(index).clone();
    }
    public int getProcessSize(){
        return processdata.size();
    }

    public void setProcessData(int index, ProcessData pd) {
        processdata.set(index, pd);
    }

    public int getMaxSize() {
        int size =0;
        for(ProcessData pdata : processdata){
            if(pdata.getSize()>size){
                size = pdata.getSize();
            }
        }
        return size;
    }
    
    public ProcessData getMaxProcessData(){
        ProcessData temp = processdata.get(0).clone();
        for(ProcessData pdata : processdata){
            if(pdata.getSize()>temp.getSize()){
                temp = pdata.clone();
            }
        }
        return temp.clone();
    
    }
    
    public String getXname(){
        return xname;
    }
    
    public void setXname(String xname){
        this.xname = xname;
    }
    
    public void setYname(String yname){
        this.yname = yname;
    }
    
    public String getYname(){
        return yname;
    }
}
