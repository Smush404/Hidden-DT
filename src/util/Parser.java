package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public class line{
        int age;
        String workclass;
        String fnlwgt;
        String education;
        int education_num;
        String marital_status;
        String occupation;
        String relationship;
        String race;
        String sex;
        int capital_gain;
        int capital_loss;
        String hours_per_week;
        String native_country;
        String  salvery;
    }

    static ArrayList<line> db = new ArrayList<line>();

    private void dbfill(ArrayList<String> words){
        int i;

        for (i = 0; i < words.size() - 1; i += 15) {
            line l = new line();

            l.age = Integer.parseInt(words.get(i));
            l.workclass = words.get(i + 1);
            l.fnlwgt = words.get(i + 2);
            l.education = words.get(i + 3);
            l.education_num = Integer.parseInt(words.get(i + 4));
            l.marital_status = words.get(i + 5);
            l.occupation = words.get(i + 6);
            l.relationship = words.get(i + 7);
            l.race = words.get(i + 8);
            l.sex = words.get(i + 9);
            l.capital_gain = Integer.parseInt(words.get(i + 10));
            l.capital_loss = Integer.parseInt(words.get(i + 11));
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
}
