import edu.princeton.cs.algs4.RedBlackBST;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author TedrosTsegay
 * Date    06/02/2023
 */

public class SearchOperation
{
    ArrayList<MovieSearch> movies;
    public SearchOperation(ArrayList<MovieSearch> newMovies)
    {
        movies = newMovies;
    }

    public RedBlackBST<Integer, HashSet<MovieSearch>> yearTree() {
        RedBlackBST<Integer, HashSet<MovieSearch>> yearRBtree = new RedBlackBST<>();

        for (int i = 0; i < movies.size(); i++) {
            if(yearRBtree.contains(movies.get(i).getYear())) {
                HashSet<MovieSearch> moviesList;
                moviesList = yearRBtree.get(movies.get(i).getYear());
                moviesList.add(movies.get(i));
                yearRBtree.put(movies.get(i).getYear(), moviesList);
            } else {
                HashSet<MovieSearch> set = new HashSet<>();
                set.add(movies.get(i));
                yearRBtree.put(movies.get(i).getYear(), set);
            }
        }
        return yearRBtree;
    }

    public RedBlackBST<Double, HashSet<MovieSearch>> scoreTree() {
        RedBlackBST<Double, HashSet<MovieSearch>> scoreRBtree = new RedBlackBST<>();
        for (int i = 0; i < movies.size(); i++) {
            if(scoreRBtree.contains(movies.get(i).getImdbScore())) {
                HashSet<MovieSearch> set;
                set = scoreRBtree.get(movies.get(i).getImdbScore());
                set.add(movies.get(i));
                scoreRBtree.put(movies.get(i).getImdbScore(), set);
            } else {
                HashSet<MovieSearch> set = new HashSet<>();
                set.add(movies.get(i));
                scoreRBtree.put(movies.get(i).getImdbScore(), set);
            }
        }
        return scoreRBtree;
    }

    public RedBlackBST<String, HashSet<MovieSearch>> languageTree() {
        RedBlackBST<String, HashSet<MovieSearch>> languageRBtree = new RedBlackBST<>();
        for (int i = 0; i < movies.size(); i++) {
            if(languageRBtree.contains(movies.get(i).getLanguage())) {
                HashSet<MovieSearch> set;
                set = languageRBtree.get(movies.get(i).getLanguage());
                set.add(movies.get(i));
                languageRBtree.put(movies.get(i).getLanguage(), set);
            } else {
                HashSet<MovieSearch> set = new HashSet<>();
                set.add(movies.get(i));
                languageRBtree.put(movies.get(i).getLanguage(), set);
            }
        }
        return languageRBtree;
    }

    public RedBlackBST<String, HashSet<MovieSearch>> ratingTree() {
        RedBlackBST<String, HashSet<MovieSearch>> rateRBtree = new RedBlackBST<>();
        for (int i = 0; i < movies.size(); i++) {
            if(rateRBtree.contains(movies.get(i).getRating())) {
                HashSet<MovieSearch> set;
                set = rateRBtree.get(movies.get(i).getRating());
                set.add(movies.get(i));
                rateRBtree.put(movies.get(i).getRating(), set);
            } else {
                HashSet<MovieSearch> set = new HashSet<>();
                set.add(movies.get(i));
                rateRBtree.put(movies.get(i).getRating(), set);
            }
        }
        return rateRBtree;
    }
}
