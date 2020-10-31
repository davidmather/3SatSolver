package com.company;

import java.util.*;

public class SatMap extends HashMap<String, boolean[]> {

    public static String[] array = {"a","b","c","d","e","f"};
    public static char[] charArray = {'a','b','c','d','e','f'};
    private static SatMap instance = null;
    static {
        instance = new SatMap();
    }


    private SatMap() {
        super();
        generateMap();
    }

    private void generateMap(){
        Set<String> set = new HashSet<>();
        for(int a = 0; a < array.length; a++){
            for(int b = 0; b < array.length; b++){
                for(int c = 0; c < array.length; c++){
                    List<String> ls = new ArrayList<>();
                    ls.add(array[a]);
                    ls.add(array[b]);
                    ls.add(array[c]);
                    Collections.sort(ls);
                    set.add(SatMap.removeDuplicates(ls.get(0)+ls.get(1)+ls.get(2)));
                }
            }
        }

        List<String> sortedList = new ArrayList<>();
        label:
        for(String s:set){
            for(int i = 0; i < array.length; i = i + 2){
                if(s.contains(array[i]+array[i+1])){
                    continue label;
                }
            }
            sortedList.add(s);
        }
        Collections.sort(sortedList);
        System.out.println(sortedList);

        int permutations = (int) Math.pow(2, array.length/2);
        boolean[][] compArray = new boolean[permutations][permutations];
        {
            final int n = array.length/2;
            int a = 0;
            int rows = (int) Math.pow(2, n);
            while (a < rows) {
                String bin = Integer.toBinaryString(a);
                while (bin.length() < n)
                    bin = "0" + bin;
                char[] chars = bin.toCharArray();
                boolean[] boolArray = new boolean[array.length];
                int k = 0;
                for (int j = 0; j < chars.length; j++) {
                    boolArray[k] = chars[j] == '0' ? true : false;
                    boolArray[k+1] = chars[j] == '0' ? false : true;
                    k = k + 2;
                }
                compArray[a] = boolArray;
                a++;
            }
        }

        boolean[][] assignments = new boolean[sortedList.size()][(int) Math.pow(2, array.length/2)]; // All possibilities is 2^n
        for(int i = 0; i < sortedList.size(); i++){

            String clause = sortedList.get(i);
            int[] ints = new int[clause.length()];
            clauseLoop:
            for(int j = 0; j < clause.length(); j++){
                for(int p = 0; p < charArray.length; p++){
                    if(clause.charAt(j) == charArray[p]){
                        ints[j] = p;
                        continue clauseLoop;
                    }
                }
            }

            for(int m = 0; m< compArray.length; m++){
                StringBuilder stringBuilder = new StringBuilder();
                boolean truthAssignment = compArray[m][ints[0]];
                for(int k = 0; k <ints.length; k++){
                    stringBuilder.append(ints[k]);
                    truthAssignment = truthAssignment && compArray[m][ints[k]];
                }
                if(!truthAssignment){
                    assignments[i][m] = true;
                }
            }

        }
        for (int i = 0; i < assignments.length; i++){
            this.put(sortedList.get(i),assignments[i]);
        }
    }

    public static String removeDuplicates(String word) {
        Set<Character> chars = new HashSet<>();
        StringBuilder output = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i); if (chars.add(ch)) { output.append(ch); }
        }
        return output.toString();
    }

    // static method to create instance of Singleton class
    public static SatMap getInstance()
    {
        return instance;
    }

    public String solve(Clause[] clauses){

        satLoop:
        for (Clause value : clauses) {
            boolean[] booleanArray = new boolean[8];
            Arrays.fill(booleanArray, Boolean.TRUE);
            outerloop:
            for (Clause clause : clauses) {
                if (value.literals.length >= clause.literals.length) {
                    int[] temp = new int[clause.literals.length];
                    System.out.println("");
                    for (int p = 0; p < clause.literals.length; p++) {
                        if(value.literals[p]>clause.literals[p] ){
                            temp[p] = value.literals[p] - clause.literals[p] + (p * 2);
                        } else {
                            temp[p] = clause.literals[p] - value.literals[p] + (p * 2);
                        }

                        if(value.literals[p] % 2 == 0) {
                            if(clause.literals[p] % 2 == 0) {
                                if (temp[p] >= 6  || temp[p] <= p * 2 - 1) {
                                    continue outerloop;
                                }
                            } else {
                                if (temp[p] >= 6  || temp[p] <= p * 2 - 2) {
                                    continue outerloop;
                                }
                            }
                        } else {
                            if(clause.literals[p] % 2 == 0) {
                                if (temp[p] >= 6  || temp[p] <= p * 2 - 1) {
                                    continue outerloop;
                                }
                            } else {
                                if (temp[p] >= 6  || temp[p] <= p * 2 - 2) {
                                    continue outerloop;
                                }
                            }
                        }
                    }

                    StringBuilder test = new StringBuilder();
                    for(int i=0; i < temp.length; i++){
                        test.append(SatMap.array[temp[i]]);
                    }
                    booleanArray = join(booleanArray, this.get(test.toString()));

                    System.out.println(Arrays.toString(temp));
                    System.out.println(Arrays.toString(clause.literals));
                    System.out.println(Arrays.toString(value.literals));
                    System.out.println("bbb");
                    System.out.println(Arrays.toString(this.get(test.toString())));
                    System.out.println(Arrays.toString(booleanArray));
                    System.out.println("");
                }
            }

            for (boolean b : booleanArray) {
                if (b) { continue satLoop; }
            }
            return "Unsatisfiable";
        }
        return "Satisfiable";
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
