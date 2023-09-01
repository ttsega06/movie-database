import edu.princeton.cs.algs4.RedBlackBST;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author TedrosTsegay
 * Date    06/02/2023
 * @param <T>
 */

public class MoviesDataBase<T extends Comparable<T>> {
    private HashMap<String, RedBlackBST<T, HashSet<MovieSearch>>> map;

    public MoviesDataBase()
    {
        map = new HashMap<>();
    }

    public void addRBT(String key, RedBlackBST<T, HashSet<MovieSearch>> tree)
    {
        map.put(key, tree);
    }

    public RedBlackBST<T, HashSet<MovieSearch>> getDB(String key)
    {
        return map.get(key);
    }

    @Override
    public boolean equals(Object obj)
    {
        return super.equals(obj);
    }
}
