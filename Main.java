import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import edu.princeton.cs.algs4.RedBlackBST;

/**
 * @author TedrosTsegay
 * Date   06/02/2023
 */

public class Main {
    private static String FILENAME = "movies.csv";
    public static void main(String[] args) {
        ArrayList<MovieSearch> movies = null;
        try {
            movies = readFile(FILENAME);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (movies == null) {
            System.out.println("Failed to read the file or the file is empty.");
            return; // Exit the main method or handle the error appropriately
        }


        //Accessing credentials for movies
        SearchOperation newSearch = new SearchOperation(movies);
        MoviesDataBase database = new MoviesDataBase<>();
        database.addRBT("Year", newSearch.yearTree());
        database.addRBT("Score", newSearch.scoreTree());
        database.addRBT("Language", newSearch.languageTree());
        database.addRBT("Rating", newSearch.ratingTree());

        userInterface(database);
    }

    public static ArrayList<MovieSearch> readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String cell = "";
        ArrayList<MovieSearch> csvArray = new ArrayList<>();
        while ((cell = reader.readLine()) != null) {
            // regex that will match strings between commas ignoring commas between quotation marks
            String[] movie = cell.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
            // skipping the first row of column names
            if (movie[0].equals("id")) {
                continue;
            }
            // checking if the movie array has the expected number of elements
            if (movie.length < 14) {
                System.out.println("Invalid movie entry: " + cell);
                continue;
            }
            // array for the three actors
            String[] actors = new String[3];
            actors[0] = movie[5];
            actors[1] = movie[6];
            actors[2] = movie[7];
            // Create a URL object to store the IMDB link
            URL link = new URL(movie[8]);
            int id = Integer.parseInt(movie[0]);
            int duration;
            //dealing with empty durations
            if(!movie[3].equals("")) {
                duration = Integer.parseInt(movie[3]);
            } else {
                duration = 0;
            }
            int year;
            //dealing with empty year values
            if(!movie[12].equals("")) {
                year = Integer.parseInt(movie[12]);
            } else {
                year = 0;
            }
            double score = Double.parseDouble(movie[13]);
            //keeping track of non-searchable info of the movies
            Movie movieInfo = new Movie(id, movie[1], movie[2], duration,
                    movie[4], actors, link, movie[10]);
            //keeping track of searchable info of the movies
            MovieSearch movieObj = new MovieSearch(year, score, movie[9], movie[11], movieInfo);
            csvArray.add(movieObj);
        }
        return csvArray;
    }

    public static void userInterface(MoviesDataBase db) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while(!exit) {
            System.out.println("Enter movie credentials: ");
            //validating year
            boolean yearFormat = false;
            String year = "";
            while (!yearFormat) {
                System.out.print("Year: ");
                year = scanner.next();
                yearFormat = year.matches("^[0-9]{4}$") || year.matches("^[-]{1}$");
                if (!yearFormat) {
                    System.out.println("Year must be a 4 digit number or '-' to skip.");
                }
            }

            //validating score
            boolean scoreFormat = false;
            String score = "";
            while(!scoreFormat) {
                System.out.print("Score: ");
                score = scanner.next();
                // Score must be a 2 digit number with one decimal value or - when not inserting a number
                scoreFormat = score.matches("^[0-9]{1}\\.[0-9]{1}$") || score.matches("^[-]{1}$");
                if (!scoreFormat) {
                    System.out.println("Score must be a two digit double such as 5.0, or '-' to skip.");
                }
            }

            boolean languageFormat = false;
            String language = "";
            while (!languageFormat) {
                System.out.print("Language: ");
                language = scanner.next();
                languageFormat = language.matches("^[A-Za-z]*$") || language.matches("^[-]{1}$");
                if (!languageFormat) {
                    System.out.println("Language must be alphabetical characters only, or '-' to skip.");
                }
            }

            boolean ratingFormat = false;
            String rating = "";
            while (!ratingFormat) {
                System.out.print("Rating: ");
                rating = scanner.next();
                ratingFormat = rating.matches("^[A-Za-z0-9-]*$") || rating.matches("^[-]{1}$");
                if (!ratingFormat) {
                    System.out.println("Rating must be alphanumeric characters and dashes only, or '-' to skip.");
                }
            }

            ArrayList<HashSet<MovieSearch>> result = new ArrayList<>();
            if (!year.equals("-")) {
                int yearValue = Integer.parseInt(year);
                RedBlackBST<Integer, HashSet<MovieSearch>> years = db.getDB("Year");
                HashSet<MovieSearch> yearResult = years.get(yearValue);
                if (yearResult != null) {
                    result.add(yearResult);
                }
            }

            if (!score.equals("-")) {
                double doubleScore = Double.parseDouble(score);
                RedBlackBST<Double, HashSet<MovieSearch>> scores = db.getDB("Score");
                HashSet<MovieSearch> scoreResult = scores.get(doubleScore);
                if (scoreResult != null) {
                    result.add(scoreResult);
                }
            }

            if (!language.equals("-")) {
                RedBlackBST<String, HashSet<MovieSearch>> languages = db.getDB("Language");
                HashSet<MovieSearch> languageResult = languages.get(language);
                if (languageResult != null) {
                    result.add(languageResult);
                }
            }

            if (!rating.equals("-")) {
                RedBlackBST<String, HashSet<MovieSearch>> ratings = db.getDB("Rating");
                HashSet<MovieSearch> ratingResult = ratings.get(rating);
                if (ratingResult != null) {
                    result.add(ratingResult);
                }
            }


            if(!result.isEmpty()) {
                for (int i = 1; i < result.size(); i++) {
                    result.get(0).retainAll(result.get(i));
                }
                List<MovieSearch> list = new ArrayList<>(result.get(0));
                Collections.sort(list);
                if(!list.isEmpty()) {
                    System.out.println();
                    System.out.println("Result (Movies -> " + (!year.equals("-") ? "year: " + year : "") +
                            (!score.equals("-") ? " score: " + score : "") +
                            (!language.equals("-") ? " language: " + language : "") +
                            (!rating.equals("-") ? " rating: " + rating : "") + ")");
                    System.out.println();
                    System.out.println(list);
                    for (MovieSearch m : list) {
                        System.out.println(m.printMovieInfo());
                    }
                } else
                    System.out.println("There are 0 movies with these characteristics.");
            } else
                System.out.println("There was an issue. Please check input!");

            // ask user if they want to check for other movies
            boolean flag = true;
            String input;
            while (flag){
                System.out.println("Would you like to search again? Enter Y to continue, or N to exit.\n");
                input = scanner.next();
                if (input.equalsIgnoreCase("y")) {
                    flag = false;
                } else if (input.equalsIgnoreCase("n")) {
                    flag = false;
                    exit = true;
                } else {
                    System.out.println("Please enter a correct option.");
                }
            }
        }
    }
}
