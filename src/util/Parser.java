package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    public class line{
        String age;
        String workclass;
        String fnlwgt;
        String education;
        String education_num;
        String marital_status;
        String occupation;
        String relationship;
        String race;
        String sex;
        String capital_gain;
        String capital_loss;
        String hours_per_week;
        String native_country;
        String  salvery;
    }

    static ArrayList<line> db = new ArrayList<line>();

    private void dbfill(ArrayList<String> words){
        int i;

        for (i = 0; i < words.size() - 1; i += 15) {
            line l = new line();

            l.age = words.get(i);
            l.workclass = words.get(i + 1);
            l.fnlwgt = words.get(i + 2);
            l.education = words.get(i + 3);
            l.education_num = words.get(i + 4);
            l.marital_status = words.get(i + 5);
            l.occupation = words.get(i + 6);
            l.relationship = words.get(i + 7);
            l.race = words.get(i + 8);
            l.sex = words.get(i + 9);
            l.capital_gain = words.get(i + 10);
            l.capital_loss = words.get(i + 11);
            l.hours_per_week = words.get(i + 12);
            l.native_country = words.get(i + 13);
            l.salvery = words.get(i + 14);


            db.add(l);
        }
    }
    
    private void parser(File f, Scanner Reader){
        ArrayList<String> words = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

            while (Reader.hasNextLine()) {

                String data = Reader.nextLine() + '\n';

                for (char c : data.toCharArray()) {
                    if (c == ',' || c == '\n') {
                        words.add(sb.toString());
                        sb.setLength(0);
                    } else if (c != ' ') {
                        sb.append(c);
                    }
                }

            }

            // for (int i = 0; i < words.size(); i++){ //DEBUG
            //     System.out.print(" " + words.get(i) + " \n");
            // }

            dbfill(words);
            Reader.close();

    }
    
    public Parser(String f){ 
        File fd = null;
        Scanner Reader = null;

        try {
            fd = new File(f);
            Reader = new Scanner(fd);
        }
        catch (FileNotFoundException e){
            System.out.println("ERROR: File not found");
            e.printStackTrace();
        }

        if(Reader != null) {
            parser(fd, Reader);
        }
    }

    public ArrayList<line> getDB(){
      return db;
    }

    public boolean is_salvery_greater_50k(line l){
        return l.salvery.equals("<=50K");
    }
    public void printDB() {
        System.out.printf("%-2s %-18s %-6s %-20s %-15s %-21s %-20s %-15s %-18s %-6s %-12s %-12s %-15s %-15s %-15s \n",
                "Age", "Workclass", "Fnlwgt", "Education", "Education_num", "Marital_Status", "Occupation",
                "Relationship", "Race", "Sex", "Capital_Gain", "Capital_Loss", "Hours_per_Week", "Native_Country", "Salary");

        for (line l : db) {
            System.out.printf("%-4s %-18s %-6s %-20s %-15s %-21s %-20s %-15s %-18s %-6s %-12s %-12s %-15s %-15s %-15s \n",
                    l.age, l.workclass, l.fnlwgt, l.education, l.education_num, l.marital_status, l.occupation,
                    l.relationship, l.race, l.sex, l.capital_gain, l.capital_loss, l.hours_per_week, l.native_country, l.salvery);
        }
    }
}
