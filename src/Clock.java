import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
public class Clock {
    public static void main(String[] args) {
        boolean a = true;
        while(a = true) {
            String input = getInput();
            if (input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
            else if (!input.contains(":")) {
                a = errorMessage();
            } else {
                if (correctFormat(delimiter(input))) {
                    talk(delimiter(input));
                } else {
                    a = errorMessage();
                }
            }    
        }
    }
    public static String getInput() {
        Scanner keyboard = new Scanner(System.in);
        String input;
        System.out.println("What is the current time in military format?");
        input = keyboard.nextLine();
        return input;
    }
    
    public static boolean correctFormat(ArrayList<String> x) {
        //Add numbers to hours array
        ArrayList<String> hours = new ArrayList();
        for (int i = 0; i < 10; i++) {
            hours.add("0" + i);
        } 
        for (int i = 10; i < 24; i++) {
            hours.add(Integer.toString(i));
        }
        //Add numbers to minutes array
        ArrayList<String> minutes = new ArrayList();
        for (int i = 0; i < 10; i++) {
            minutes.add("0" + i);
        } 
        for (int i = 10; i < 61; i++) {
            minutes.add(Integer.toString(i));
        }
        //Determine if its in correct format.
        boolean a = false;
        if (hours.contains(x.get(0)) && minutes.contains(x.get(1))) {
            a = true;
        } 
        return a;
    }
    
    public static ArrayList<String> delimiter(String x) {
        ArrayList<String> parts = new ArrayList<>(Arrays.asList(x.split(":")));
        return parts;
    }
    
    public static boolean errorMessage() {
        boolean quit = true;
        boolean loop = true;
        while (loop) {
            System.out.println("You have entered something invalid. Would you like to try again? Y/N");
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();
            if (input.equalsIgnoreCase("N")) {
                System.exit(0);
            } else if (input.equalsIgnoreCase("Y")) {
                quit = true;
                loop = false;
            } else {
                System.out.println("Please enter a valid choice ");
            }
        }
        return quit;
    }
    public static void talk(ArrayList<String> x) {
        //Add items for arraylist hours
        ArrayList <String> hours = new ArrayList();
        hours.add("one "); hours.add("two "); hours.add("three "); hours.add("four "); hours.add("five ");
        hours.add("six "); hours.add("seven "); hours.add("eight "); hours.add("nine "); hours.add("ten "); hours.add("eleven "); hours.add("twelve "); 
        
        //Add items for arraylist minutes
        ArrayList <String> minutes = new ArrayList();
        try {
            BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\Heather\\Desktop\\minutes.txt"));
            Scanner reader = new Scanner(read);
            String line = reader.nextLine();
            while (reader.hasNextLine()) {
                minutes.add(line);
                line = reader.nextLine();
            }
            read.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        //Mix input and produce string
        String ampm = null;
        if (Integer.parseInt(x.get(0)) > 11) {
            ampm = "PM";
        } else {
            ampm = "AM";
        }
        
        String hour = null;
        if (x.get(0).equals("00") || x.get(0).equals("12")) {
            hour = "twelve";
        } else if (x.get(0).equals("1") || x.get(0).equals("13")) {
            hour = "one";
        } else if (x.get(0).equals("2") || x.get(0).equals("14")) {
            hour = "two";
        } else if (x.get(0).equals("3") || x.get(0).equals("15")) {
            hour = "three";
        } else if (x.get(0).equals("4") || x.get(0).equals("16")) {
            hour = "four";
        } else if (x.get(0).equals("5") || x.get(0).equals("17")) {
            hour = "five";
        } else if (x.get(0).equals("6") || x.get(0).equals("18")) {
            hour = "six";
        } else if (x.get(0).equals("7") || x.get(0).equals("19")) {
            hour = "seven";
        } else if (x.get(0).equals("8") || x.get(0).equals("20")) {
            hour = "eight";
        } else if (x.get(0).equals("9") || x.get(0).equals("21")) {
            hour = "nine";
        } else if (x.get(0).equals("10") || x.get(0).equals("22")) {
            hour = "ten";
        } else if (x.get(0).equals("11") || x.get(0).equals("23")) {
            hour = "eleven";
        }
        System.out.println("The time is " + hour + " " + minutes.get(Integer.parseInt(x.get(1))) + " " + ampm + ".");
        
    }
}
