package com.company;

import java.util.*;

public class Main {

    static SatMap satMap = SatMap.getInstance();
    public static void main(String []args){
        Clause[] clauses = new Clause[4];
        clauses[0] = new Clause(new int[]{1, 4, 5});
        clauses[1] = new Clause(new int[]{0, 5, 6});
        clauses[2] = new Clause(new int[]{0, 0, 0});
        clauses[3] = new Clause(new int[]{1, 1, 1});

//        Clause[] clauses = new Clause[2];
//        clauses[0] = new Clause(new int[]{6, 1, 2});
//        clauses[1] = new Clause(new int[]{1, 2, 3});

//        clauses[4] = new Clause(new int[]{8, 9, 10});
//        clauses[5] = new Clause(new int[]{11, 12, 11});
//        clauses[6] = new Clause(new int[]{13, 14, 15});
//        clauses[7] = new Clause(new int[]{16, 17, 18});
//        clauses[8] = new Clause(new int[]{19, 21, 22});
//        clauses[9] = new Clause(new int[]{24, 1, 2});
        System.out.println(satMap.solve(clauses));
    }

}
