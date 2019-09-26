import java.util.Scanner;
import java.io.File;

public class SourceModel {

    private static String source;
    private String corpusFile;
    private double[][] matrix;
    private double probability;

    public SourceModel(String sourceName, String fileName) {
        source = sourceName;
        corpusFile = fileName;
        double[][] probMatrix = new double[26][26];
        System.out.printf("Training %s model ... ", source);

        File file = new File(fileName);
        try {

            // scanner that reads through and makes a matrix of counts of word
            // transitions

            Scanner reader = new Scanner(file);
            while (reader.hasNext()) {
                int place1 = 0;
                int place2 = 1;
                String str = reader.nextLine();
                str = str.replaceAll("\\.", " ");
                str = str.replaceAll("\\ ", " ");
                str = str.replaceAll("\\'", " ");
                str = str.replaceAll("\\,", " ");
                str = str.replaceAll("\\!", " ");
                str = str.replaceAll("\\?", " ");
                str = str.replaceAll("\\’", " ");
                str = str.replaceAll("\\/", " ");
                str = str.replaceAll("\\]", " ");
                str = str.replaceAll("\\[", " ");
                str = str.replaceAll("\\:", " ");
                str = str.replaceAll("\\;", " ");
                str = str.replaceAll("\\-", " ");
                str = str.replaceAll("\\“", " ");
                str = str.replaceAll("\\@", " ");
                str = str.replaceAll("\\%", " ");
                str = str.replaceAll("\\$", " ");


                str = str.toLowerCase();
                for (int i = 0; i < (str.length() - 1); i++) {
                    if (Character.isLetter(str.charAt(place1))) {
                        if (Character.isLetter(str.charAt(place2))) {
                            char char1 = str.charAt(place1);
                            char char2 = str.charAt(place2);
                            int letter1 = char1 - 97;
                            int letter2 = char2 - 97;
                            System.out.println(letter1);
                            System.out.println(letter2);
                            probMatrix[letter2][letter1]++;
                        }
                    }
                    place1++;
                    place2++;
                }
            }

            // takes the matric that was just made and divides each unit in a
            // row by the sum of that row to get the percentage.

            double sum = 0;
            for (int p = 0; p < 26; p++) {
                for (int q = 0; q < 26; q++) {
                    sum += probMatrix[p][q];
                }
                for (int r = 0; r < 26; r++) {
                    if (probMatrix[p][r] != 0) {
                        probMatrix[p][r] = probMatrix[p][r] / sum;
                    } else {
                        probMatrix[p][r] = 0.01;
                    }
                }
                sum = 0.0;
            }
            // for (int p = 0; p < 26; p++) {
            //     for (int q = 0; q < 26; q++) {
            //         System.out.print("[" + probMatrix[p][q] + "]  ");
            //     }
            //     System.out.println();
            // }
            System.out.println("done");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.source = source;
        this.matrix = probMatrix;
    }

    // my probability method that takes in a string and determines the
    // probability that the string is that .corpus file.
    public double probability(String phrase) {
        String  newStr = phrase;
        int place1 = 0;
        int place2 = 1;
        newStr = newStr.replaceAll("\\.", " ");
        newStr = newStr.replaceAll("\\ ", " ");
        newStr = newStr.replaceAll("\\'", " ");
        newStr = newStr.replaceAll("\\,", " ");
        newStr = newStr.replaceAll("\\!", " ");
        newStr = newStr.replaceAll("\\?", " ");
        newStr = newStr.replaceAll("\\’", " ");
        newStr = newStr.replaceAll("\\/", " ");
        newStr = newStr.replaceAll("\\]", " ");
        newStr = newStr.replaceAll("\\[", " ");
        newStr = newStr.replaceAll("\\:", " ");
        newStr = newStr.replaceAll("\\;", " ");
        newStr = newStr.replaceAll("\\-", " ");
        newStr = newStr.replaceAll("\\“", " ");
        newStr = newStr.replaceAll("\\@", " ");
        newStr = newStr.replaceAll("\\%", " ");
        newStr = newStr.replaceAll("\\$", " ");
        newStr = newStr.toLowerCase();
        //System.out.println(newStr);
        double probs = 1.0;
        double[][] arry = this.matrix;
        for (int i = 0; i < (newStr.length() - 1); i++) {
            if (Character.isLetter(newStr.charAt(place1))) {
                if (Character.isLetter(newStr.charAt(place2))) {
                    char char1 = newStr.charAt(place1);
                    char char2 = newStr.charAt(place2);
                    int letter1 = char1 - 97;
                    int letter2 = char2 - 97;
                    probs = probs * arry[letter2][letter1];
                }
            }
            place1++;
            place2++;
        }
        this.probability = probs;
        //System.out.println(probs);
        return probs;

    }

    // main method that takes names of corpus files and a string as inputs
    // it will find the transition probability matrix of the corpus files and
    // then compare the string input transitions to each transition matrix.
    // it will then return a probability that the string belongs to the
    // corpus file.

    public static void main(String[] args) {
        String[] files = new String[(args.length) - 1];
        String check = args[args.length - 1];
        String[] models = new String[(args.length) - 1];
        double[] probabilities = new double[(args.length) - 1];
        SourceModel[] arr = new SourceModel[args.length - 1];

        // creates an array of corpus file names
        for (int p = 0; p < args.length - 1; p++) {
            files[p] = args[p];
        }

        // creates an array of model names and then creates an object with that
        // model
        for (int p = 0; p < files.length; p++) {
            String[] split = files[p].split("[.]");
            models[p] = split[0];
            arr[p] = new SourceModel(models[p], files[p]);
            //System.out.println(models[p]);
        }

        //for each file create an object of SourceModel
        System.out.println("Analyzing: " + check);
        double sum = 0.0;
        double topPercent = arr[0].probability(check);
        String topSource = models[0];
        for (int p = 0; p < files.length; p++) {
            probabilities[p] = arr[p].probability(check);
            if (probabilities[p] > topPercent) {
                topPercent = probabilities[p];
                topSource = models[p];
            }
            sum += arr[p].probability(check);
           //System.out.println(probabilities[p]);
        }

        //normalizes the probabilities
        for (int q = 0; q < probabilities.length; q++) {
            probabilities[q] = probabilities[q] / sum;
            String probability1 = "" + probabilities[q];
            probability1 = probability1.format("%.2f", probabilities[q]);
            System.out.println("the probability the test string is "
                + models[q] + " is: " + probability1);
        }
        System.out.println("Test string is most likely " + topSource);
    }

    public String toString() {
        String aString = "     a    b    c    d    e    f    g    h    i    j";
        aString += "    k    l    m    n    o    p    q    r    s    t    u";
        aString += "    v    w    x    y    z\n";
        int letter = 97;
        for (int row = 0; row < matrix.length; row++) {
            aString += aString.format("%s", (char) letter++);
            for (int col = 0; col < matrix[row].length; col++) {
                aString += " " + aString.format("%.2f", matrix[row][col]);
            }
            aString += "\n";
        }
        return aString;
    }

    public String getName() {
        return source;
    }

}
