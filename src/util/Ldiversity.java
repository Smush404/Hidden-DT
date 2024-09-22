package util;

public class Ldiversity {
    //                                   0                   1           2           3           4              5                      6
    private final String[][] table = {{"Agriculture", "Armed-Service", "Service", "Management", "Worker", "Physical-Specialist", "Specialist"}, //Lvl 1
    //                                    0                  1           2               3
                                      {"Agriculture", "Service", "Office-Worker", "Specialist"},  //Lvl 2
    //                                   0                   1
                                      {"Non-White_Collar", "White-Collar"}, //Lvl 3
    //                                   0
                                      {"*"}}; //Lvl 4
    public Ldiversity(){}

    private Parser.line prep(Parser.line line){
        if(line.occupation.equals("?")){
            line.occupation = "*";
        }

        return line;
    }
    public Parser.line L5(Parser.line l){
        Parser.line line = prep(l);

        if(!line.occupation.equals("*")) {

            //first 4
            switch (line.occupation.substring(0, 4)) {
                case "Exec":
                case "Adm-":
                case "Tech":
                    line.occupation = "Office";
                    break;
                case "Prof":
                    line.occupation = "Specalist";
                    break;
            }

            //first 5
            switch (line.occupation.substring(0, 5)) {
                case "Other":
                case "Armed":
                    line.occupation = "Service";
                case "Sales":
                    line.occupation = "Office";
                    break;
                case "Craft":
                    line.occupation = "Specalist";
                    break;
            }

            if (line.occupation.length() >= 7) {
                //first 7
                switch (line.occupation.substring(0, 8)) {
                    case "Handlers":
                    case "Transpor":
                    case "Protecti":
                        line.occupation = "Service";
                        break;
                    case "Machine-":
                        line.occupation = "Specalist";
                        break;
                    case "Farming-":
                        line.occupation = "Agriculture";
                        break;
                }
            }
        }
        //System.out.print(line.occupation + ", ");
        return line;
    }

    public  Parser.line init_LR(Parser.line l, int Wanted_lvl){
        int num = 0;
        Parser.line line = prep(l);

        if(!line.occupation.equals("*")) {

            //first 4
            num = switch (line.occupation.substring(0, 4)) {
                case "Exec", "Adm-" -> 3;
                case "Tech" -> 4;
                case "Prof" -> 6;
                default -> num;
            };

            //first 5
            num = switch (line.occupation.substring(0, 5)) {
                case "Other", "Sales" -> 4;
                case "Armed" -> 1;
                case "Craft" -> 5;
                default -> num;
            };

            if (line.occupation.length() >= 7) {
                //first 7
                num = switch (line.occupation.substring(0, 8)) {
                    case "Handlers", "Transpor" -> 2;
                    case "Protecti", "Machine-" -> 6;
                    default -> num;
                };
            }
        }



        l = LR(l, 0, num, Wanted_lvl - 1);


        return l;
    }

    private Parser.line LR(Parser.line line, int lvl, int occ_num, int want_lvl){

        if(lvl == want_lvl){
            line.occupation = table[lvl][occ_num];
            return line;
        }
        if(lvl == 3 || want_lvl == 3){
            line.occupation = "*";
            return line;
        }

        if(lvl == 0){
            switch (occ_num){
                case 4:
                case 5:
                    occ_num -= 2;
                    break;
                case 2:
                case 3:
                    occ_num--;
                    break;
                case 6:
                    occ_num /= 2;
                    break;
            }
        }
        else if(lvl == 1){
            switch (occ_num){
                case 1:
                    occ_num--;
                    break;
                case 2:
                case 3:
                    occ_num = 1;
            }
        }
        else{
           occ_num = 0;
        }

        lvl++;
        return LR(line, lvl, occ_num,want_lvl);
    }

}
