//Heather Tran

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class Clock {

    public static void main(String[] args) {
        boolean a = true;
        String input = getInput();
        while (a) { //This loop runs the entire program. Exit and all.
            if (input.equalsIgnoreCase("1")) {
                getInput(); //This gets input from the user
            } else if (input.equalsIgnoreCase("2")) {
                a = false; //This quits
            } else if (!input.contains(":")) { //A check for invalid format
                a = errorMessage();  //Calls the error message
            } else {
                if (correctFormat(delimiter(input))) { //Checks if correctFormat using the input from user and delimiter method
                    talk(delimiter(input)); //Calls talk method to get the talking string
                } else {
                    a = errorMessage(); //Error message because input is wrong format
                }
            }
        }
    }

    public static String getInput() { //This takes input from the user.
        Scanner keyboard = new Scanner(System.in);
        String input;
        System.out.println("What is the current time in military format?");
        input = keyboard.nextLine();
        return input;
    }

    public static boolean correctFormat(ArrayList<String> x) { //Creates two arraylists for hours and minutes and checks format
        ArrayList<String> hours = new ArrayList(); //Add numbers to hours array
        for (int i = 0; i < 10; i++) {
            hours.add("0" + i);
        }
        for (int i = 10; i < 24; i++) {
            hours.add(Integer.toString(i));
        }
        ArrayList<String> minutes = new ArrayList(); //Add numbers to minutes array
        for (int i = 0; i < 10; i++) {
            minutes.add("0" + i);
        }
        for (int i = 10; i < 61; i++) {
            minutes.add(Integer.toString(i));
        }
        boolean a = false; //Determine if its in correct format.
        if (hours.contains(x.get(0)) && minutes.contains(x.get(1))) { //This checks to see if the parts exist in either of the arrays
            a = true;
        }
        return a;
    }

    public static ArrayList<String> delimiter(String x) { //This method splits the user's input into 2 in an arraylist
        ArrayList<String> parts = new ArrayList<>(Arrays.asList(x.split(":")));
        return parts;
    }

    public static boolean errorMessage() { //This states the error message and gets if the user wants to quit.
        boolean loop = true;
        while (loop) {
            System.out.println("You have entered something invalid. Would you like to try again? Y/N");
            Scanner keyboard = new Scanner(System.in);
            String input = keyboard.nextLine();
            if (input.equalsIgnoreCase("N")) {
                return false;
            } else if (input.equalsIgnoreCase("Y")) {
                return true;
            } else {
                System.out.println("Please enter a valid choice below.");
            }
        }
        return true;
    }

    public static void talk(ArrayList<String> x) {
        ArrayList<String> hours = new ArrayList();
        try { //Add items for arraylist hours
            BufferedReader read = new BufferedReader(new FileReader("hours.txt"));
            String line = read.readLine();
            while (line != null) {
                hours.add(line);
                line = read.readLine();
            }
            read.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        ArrayList<String> minutes = new ArrayList();
        try { //Add items for arraylist minutes
            BufferedReader read = new BufferedReader(new FileReader("minutes.txt"));
            String line = read.readLine();
            while (line != null) {
                minutes.add(line);
                line = read.readLine();
            }
            read.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        String ampm = null; //This checks if the time is AM or PM
        if (Integer.parseInt(x.get(0)) > 11) {
            ampm = "PM";
        } else {
            ampm = "AM";
        }
        System.out.println("The time is " + hours.get(Integer.parseInt(x.get(0))) + " " + minutes.get(Integer.parseInt(x.get(1))) + " " + ampm + ".");
    }
}
