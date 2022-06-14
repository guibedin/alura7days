package guibedin.alura7days;

import guibedin.alura7days.day3.jackson.Day3Jackson;
import guibedin.alura7days.day3.parse.Day3;
import guibedin.alura7days.day3.record.Day3Record;

public class Main {

    public static void main(String[] args) {
        try {
//            Day1.execute();
//            Day2.execute();
            Day3.execute();
            System.out.println("==============================");
            Day3Jackson.execute();
            System.out.println("==============================");
            Day3Record.execute();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
