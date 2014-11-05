package DataFlow;

public class Pair<Key,Value>{
    private Key x;
    private Value y;
    
    public Pair(Key x,Value y){
        this.x = x;
        this.y = y;
    }

    Pair() {
        
    }
    

    public void setKey(Key x){
        this.x = x;
    }
    
    public void setValue(Value y){
        this.y = y;
    }
    
    public Key getX(){
        return x;
    }
    
    public Value getY(){
        return y;
    }
    
    @Override
    public Pair clone(){
        return new Pair(x,y);
    }

    public boolean isSymbol() {
        switch(this.x.toString()){
            case "ST_ID":
                return true;
            case "Class_ID":
                return true;
            case "SUBMITION_ID":
                return true;
            default:
                return false;
        }
    }
    
    
}
