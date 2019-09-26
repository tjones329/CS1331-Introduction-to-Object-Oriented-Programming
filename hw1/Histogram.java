import java.io.*;
import java.util.Scanner;

public class Histogram {
    public static void main(String[] args) throws IOException {

        // Creates variables for file name and number of intervals and then
        // creates an array based on the amount of intervals specified
        String newFile = args[0];
        String arg1 = "";
        if (args.length < 2){
            Scanner input = new Scanner(System.in); //opens a scanner, keyboard
            System.out.print("Enter # of bins: "); //prompt the user
            arg1 = input.nextLine(); //store the input from the user
        }
        else {
            arg1 = args[1];
        }
        int intervalsNum = Integer.parseInt(arg1);
        int intervalsArray[] = new int[intervalsNum];
        int intervalLength = 0;
        // gets length of the intervals
        if (intervalsNum == 101){
            intervalLength = 1  ;
        }
        else{
            intervalLength = 100 / intervalsNum;
        }

        // Creates a new scanner for the filename given and goes through each
        // line in the file, getting the grade value from the line and
        // converts the grade value from type string to type int
        Scanner gradeFile = new Scanner(new File(newFile));
        while (gradeFile.hasNext()) {
            String line = gradeFile.nextLine();
            String[] line1 = line.split(",");
            String line2 = line1[1].trim();
            int result = Integer.parseInt(line2);
            //System.out.println(result);

            // Finds the interval that the Grade is in
            int intervalCount = intervalLength;
            int positionCount = 0;
            while (result > intervalCount) {
                intervalCount += intervalLength;
                positionCount += 1;
            }

            intervalsArray[positionCount] += 1;
            //System.out.println(intervalsArray[positionCount]);
        }

        // for each element in the array, does an print line with the correct
        // amount of [] as specified with the integer in that position.
        int newNum = intervalsArray.length;
        int minCount = 100;
        int maxCount = 101 - intervalLength;
        String linePrint = minCount + " - " + maxCount + " | ";
        minCount -= (intervalLength + 1);
        maxCount -= intervalLength;
        for (int j = 0; j < intervalsArray[newNum-2]; ++j){
            linePrint += "[]";
            }
        newNum -= 1;
        minCount += 1;
        System.out.println(linePrint);
        for (int i = 1; i < (intervalsArray.length-1); ++i){
            if (minCount < 100 & minCount > 10){
               linePrint = " " + minCount + " - " + maxCount + " | ";
            }
            if (maxCount < 100){
               linePrint = " " + minCount + " - " + maxCount + " | ";
            }
            if (minCount == 10){
               linePrint = " " + minCount + " - " + maxCount + " | ";
            }
            if (maxCount < 10){
               linePrint = "  " + minCount + " -  " + maxCount + " | ";
            }
            if (minCount == 10 & maxCount < 10){
               linePrint = " " + minCount + " -  " + maxCount + " | ";
            }
            //linePrint = minCount + " - " + maxCount + " | ";
            minCount -= intervalLength;
            maxCount -= intervalLength;
            for (int j = 0; j < (intervalsArray[newNum-1]); ++j){
                linePrint += "[]";
            }
            newNum -= 1;
            System.out.println(linePrint);
        }
        if (minCount < 100 & minCount > 10){
           linePrint = " " + minCount + " - " + maxCount + " | ";
        }
        if (maxCount < 100){
           linePrint = " " + minCount + " - " + maxCount + " | ";
        }

        if (minCount < 10){
           linePrint = " " + minCount + " - " + maxCount + " | ";
        }
        if (maxCount < 10){
            linePrint = " " + minCount + " -  " + maxCount + " | ";
            if (minCount < 10 & minCount != 0){
                linePrint = "  " + minCount + " -  " + (maxCount - 1)+ " | ";
            }
            if (minCount == 0){
                linePrint = "  " + minCount + " -  " + (maxCount)+ " | ";
            }
        }
        for (int j = 0; j < intervalsArray[newNum-1]; ++j){
            linePrint += "[]";
            }
        newNum -= 1;
        minCount += 1;
        System.out.println(linePrint);
    }
}
