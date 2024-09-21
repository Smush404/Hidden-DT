import util.Kanonymity;
import util.Ldiversity;
import util.Parser;

import java.util.ArrayList;

public class Main{
    public static void main(String[] args){

        Parser p = new Parser("data/adult.data");

        ArrayList<Parser.line> db = p.getDB();
        Parser.line l;

        Kanonymity k = new Kanonymity();

        for(int i = 0; i < db.size(); i++){
            l = db.get(i);
            db.remove(i);

            if(p.is_salvery_greater_50k(l)){
                db.add(i, k.k10(l));
            }else{
                db.add(i, k.k5(l));
            }

        }

        Ldiversity L = new Ldiversity();

        for(int i = 0; i < db.size(); i++){
            l = db.get(i);
            db.remove(i);

            db.add(i, L.L5(l));
        }

        p.printDB();

    System.out.println("<======Done!======>");
    }
}