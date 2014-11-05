package DataFlow;

import java.util.ArrayList;

public class ProcessData <Key,Value>{
    ArrayList<Pair<Key,Value>> pairList;
    String xname;
    String yname;
    
    public ProcessData(Key x,Value y,String xname,String yname, int id){
        pairList.add(new Pair(x,y));
        this.xname = xname;
        this.yname = yname;
    }

    public ProcessData(String xname,String yname, int id) {
        this.pairList  = new ArrayList();;
        this.xname = xname;
        this.yname = yname;
    }
    
    public ProcessData(String xname,String yname) {
        this.pairList  = new ArrayList();;
        this.xname = xname;
        this.yname = yname;
    }
        
    public Pair<Key,Value> getPair(int index){
        ArrayList<Pair<Key,Value>> temp = new ArrayList();
        for(Pair pair : pairList){
            temp.add(pair);
        }
        return temp.get(index);
    }
    
    public Pair<Key,Value> getPair(String key){
        Pair<Key,Value> temp = new Pair();
        for(Pair p : pairList){
            if(p.getX().toString().equals(key)){
                temp = p;
            }
        }
        return temp;
    }
    
    public ArrayList<Pair<Key,Value>> getData(){
        ArrayList<Pair<Key,Value>> temp = new ArrayList();
        for(Pair pair : pairList){
            temp.add(pair);
        }
        return temp;
    }
    
    public void setDatas(ArrayList<Pair<Key,Value>> pairList){
        this.pairList = pairList;
    }
    
    public void addData(Pair pair){
        this.pairList.add(pair);
    }
    
    public String getXname(){
        return this.xname;
    }
    
    public void setXname(String xname){
        this.xname = xname;
    }
    
    public String getYname(){
        return yname;
    }
    
    public void setYname(String yname){
        this.yname = yname;
    }
    
    public void removePair(int index){
        this.pairList.remove(index);
    }
    
    public ProcessData getRealData(){
        ProcessData pd = new ProcessData(this.getXname(),this.getYname());
        for(Pair pair : pairList){
            if(!pair.isSymbol()){
                pd.addData(pair);
            }
        }
        return pd;
    }
    
    @Override
    public ProcessData clone(){
        ArrayList<Pair<Key,Value>> temppairList = new ArrayList();
        for(Pair p : pairList){
            temppairList.add(p);
        }
        ProcessData processData = new ProcessData(this.xname,this.yname);
        processData.setDatas(temppairList);
        return processData;        
    }
    
    public int getSize(){
        return pairList.size();
    }

    public int getRealSize() {
        int temp = this.pairList.size();
        for(Pair p : pairList){
            if(p.isSymbol()){
            temp--;
            }
        }
        return temp;
    }

    public void setPair(int pairindex, Pair pair) {
        pairList.set(pairindex, pair);
    }
}
