import java.util.*;

import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.toMap;

/**
 * Created by hadoop on 17-4-4.
 */
public class MirsaGries<T> implements iMirsaGries<T> {
    private int size;
    private Map<T, Integer> countersMap;
//    private NavigableMap<Integer, Set<T>> counts;

    public MirsaGries(int size){
        this.size = size;
        this.countersMap = new HashMap<T, Integer>();
//        this.counts = new TreeMap<>();
    }

    @Override
    public void insert(T data){
        Integer count = countersMap.get(data);
        if(count != null){
//            Set<T> elements = counts.get(count);
//            if(elements.isEmpty()){
//                counts.remove(count);
//            }
            doAdd(data, count+1);
        }else if(countersMap.size() < size-1){
            doAdd(data, 1);
        }else{
            Iterator<Map.Entry<T, Integer>> it = countersMap.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<T, Integer> entry = it.next();
                int c = entry.getValue();
                c -= 1;
                countersMap.put(entry.getKey(), c);
                if(c <= 0){
                    it.remove();
//                    counts.remove(c);
                }
            }
        }
    }


    private void doAdd(T element, int count){
        countersMap.put(element, count);
//        Set<T> elements = counts.get(count);
//        if(elements == null){
//            elements = new HashSet<>();
//            counts.put(count, elements);
//        }
//        elements.add(element);
    }

    @Override
    public Map<T, Integer> frequencies(){
        return unmodifiableMap(countersMap);
    }


    @Override
    public Map<T, Integer> sortedFrequencies(){
        return unmodifiableMap(countersMap.entrySet().stream()
                .sorted(Map.Entry.<T, Integer>comparingByValue().reversed())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new)));
    }



}
