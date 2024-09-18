import util.Parser;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args){
        Parser p = new Parser("data/adult.data");
        System.out.println(p.getDB().size());

        ArrayList<Parser.line> db = p.getDB();
    }
}