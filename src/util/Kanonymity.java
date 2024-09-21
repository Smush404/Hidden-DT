package util;

import java.util.ArrayList;

public class Kanonymity {

    public Kanonymity(){}//empty constructor

    private  Parser.line SupressData(Parser.line line){
        line.native_country = "*";
        line.hours_per_week = "*";
        line.capital_loss = "*";
        line.capital_gain = "*";
        line.sex = "*";
        line.relationship = "*";
        line.education_num = "*";
        line.fnlwgt = "*";

        if(line.occupation == "?"){
            line.age = "*";
        }

        return line;

    }
    public Parser.line k10(Parser.line line){ // above 50K edit

        line = SupressData(line);
        //Age
        if(Integer.parseInt(line.age) <= 40){
            line.age = ">=40";
        }
        else if(Integer.parseInt(line.age) > 40){
            line.age = "<40";
        }

        //Education
        switch (line.education) {
            case "Pre-school":
            case "1st-4th":
            case "5th-6th":
            case "7th-8th":
            case "9th":
            case "10th":
            case "11th":
            case "12th":
            case "HS-grad":
            case "Some-college":
                line.education = "Lower-Education";
                break;
            case "Assoc-voc":
            case "Assoc-acdm":
            case "Bachelors":
            case "Masters":
            case "Doctorate":
            case "Prof-school":
                line.education = "Higher-Education";
                break;

        }

        //Race
        if(!line.race.equals("Black") && !line.race.equals("White")){
            line.race = "Other";
        }

        //Marital status
        switch (line.marital_status){
            case "Married-civ-spouse":
            case "Married-spouse-absent":
            case "Married-AF-spouse":
                line.marital_status = "Married";
                break;
            case "Divorced":
            case "Single":
            case "Widowed":
                line.marital_status = "Single";
                break;
        }

        return line;
    }

    public Parser.line k5(Parser.line line){ // below 50K edit
        line = SupressData(line);
        //Age
        if(Integer.parseInt(line.age) < 30){
            line.age = ">30";
        }
        else if(Integer.parseInt(line.age) < 40){
            line.age = "<40";
        }
        else if(Integer.parseInt(line.age) < 50){
            line.age = "<50";
        }
        else if(Integer.parseInt(line.age) >= 50){
            line.age = ">=50";
        }

        //Education
        switch (line.education) {
            case "Pre-school":
            case "1st-4th":
            case "5th-6th":
            case "7th-8th":
                line.education = "Pre-HS";
                break;
            case "9th":
            case "10th":
            case "11th":
            case "12th":
                line.education = "High-School";
                break;
            case "HS-grad":
            case "Some-college":
                line.education = "Lower-Education";
                break;
            case "Assoc-voc":
            case "Assoc-acdm":
                line.education = "Associates";
                break;
            case "Bachelors":
            case "Masters":
            case "Doctorate":
                line.education = "Higher-Education";
                break;
            case "Prof-school":
                line.education = "Professal-School";
                break;

        }

        //Race
        if(!line.race.equals("Black") ^ !line.race.equals("White")){
            line.race = "Other";
        }

        //Marital status
        switch (line.marital_status){
            case "Married-civ-spouse":
            case "Married-spouse-absent":
            case "Married-AF-spouse":
                line.marital_status = "Married";
                break;
            case "Divorced":
            case "Single":
                line.marital_status = "Single";
                break;
            case "Widowed":
                line.marital_status = "Widowed";
                break;
        }

        return line;
    }

}
