package DataMapper.one;

import DataFlow.DataSet;
import DataFlow.Pair;

public abstract class One_DataMapper {
    protected abstract DataSet map(DataSet ds);
    protected boolean cheachPair(Pair pairA, Pair pairB) {
        return pairA.getX().toString().equals(pairB.getX().toString());
    }
}
