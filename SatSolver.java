package com.company;

import java.util.*;

public class SatSolver {

    static SatMap satMap = SatMap.getInstance();

    public static String solve(Clause[] clauses){

        for (Clause value : clauses) {
            boolean[] booleanArray = new boolean[8];
            Arrays.fill(booleanArray, Boolean.TRUE);
            outerloop:
            for (Clause clause : clauses) {
                if (value.literals.length >= clause.literals.length) {
                    int[] temp = new int[clause.literals.length];
                    for (int p = 0; p < clause.literals.length; p++) {
                        if(value.literals[p]>clause.literals[p] ){
                            temp[p] = value.literals[p] - clause.literals[p] + (p * 2);
                        } else {
                            temp[p] = clause.literals[p] - value.literals[p] + (p * 2);
                        }
                        //System.out.println();
                        //System.out.println(value.literals[p]+ " even "+(p * 2));
                        //System.out.println(temp[p]);
                        //System.out.println(Arrays.toString(value.literals));
                        //System.out.println(Arrays.toString(clause.literals));
                        if(value.literals[p] % 2 == 0) {
                            if(clause.literals[p] % 2 == 0) {
                                if (temp[p] >= 6  || temp[p] <= p * 2 - 1) {
                                    continue outerloop;
                                }
                                temp[p] = p * 2;
                            } else {
                                if (temp[p] >= 6  || temp[p] <= p * 2 - 2) {
                                    continue outerloop;
                                }
                                temp[p] = p * 2 + 1;
                            }
                        } else {
                            if(clause.literals[p] % 2 == 0) {
                                if (temp[p] >= 6  || temp[p] <= p * 2 - 1) {
                                    continue outerloop;
                                }
                                temp[p] = p * 2;
                            } else {
                                if (temp[p] >= 6  || temp[p] <= p * 2 - 2) {
                                    continue outerloop;
                                }
                                temp[p] = p * 2 + 1;
                            }
                        }
                    }
                    System.out.println("result");
                    System.out.println(Arrays.toString(value.literals));
                    System.out.println(Arrays.toString(clause.literals));
                    System.out.println(Arrays.toString(temp));

                    StringBuilder test = new StringBuilder();
                    for(int i=0; i < temp.length; i++){
                        test.append(SatMap.array[temp[i]]);
                    }
                    System.out.println(test.toString());
                    System.out.println(Arrays.toString(satMap.get(test.toString())));

                    booleanArray = join(booleanArray, satMap.get(test.toString()));

                    System.out.println(Arrays.toString(booleanArray));
                }
            }

            boolean truetest = false;
            for(int i = 0; i < booleanArray.length; i++){
                if(booleanArray[i]){
                    truetest = true;
                }
            }
            if(!truetest){
                return "Unsatisfiable";
            }
        }
        return "Satisfiable";
    }

    private static String removeDuplicates(String word) {
        Set<Character> chars = new HashSet<>();
        StringBuilder output = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i); if (chars.add(ch)) { output.append(ch); }
        }
        return output.toString();
    }


    public static boolean[] join(boolean[] a, boolean[]b) {
        for(int i = 0; i < b.length; i++){
            if(!b[i]){
                a[i] = false;
            }
        }
        return a;
    }



}
