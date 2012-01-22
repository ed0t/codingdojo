package net.devonlinux.datamunging.core;

import java.util.Iterator;
import java.util.List;

public class SpreadFinder {

    public SpreadInformation find(List<SpreadInformation> data){
        if(data.size()<1){
            throw new RuntimeException("There are no elements in the list");
        }

        Iterator<SpreadInformation> iterator = data.iterator();
        SpreadInformation temporary = iterator.next();
        while (iterator.hasNext()){
            SpreadInformation current = iterator.next();
            if(current.spread() < temporary.spread()){
                temporary = current;
            }
        }
        return temporary;
    }


    public static SpreadFinder aSpreadFinder() {
        return new SpreadFinder();
    }
}
