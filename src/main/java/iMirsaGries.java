import java.util.Map;

/**
 * Created by hadoop on 17-4-4.
 */
public interface iMirsaGries<T> {

    void insert(T data);

    Map<T, Integer> frequencies();

    Map<T, Integer> sortedFrequencies();
}
