package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Clause {

    public int[] literals;

    /**
     * @param literals
     */
    public Clause(int[] literals) {
        this.literals = removeDuplicateIntegersAndSort(literals);
    }

    private static int[] removeDuplicateIntegersAndSort(int[] literals) {
        int[] temp = new int[literals.length];
        int n = 0;
        loop:
        for (int i = 0; i < literals.length; i++) {
            for (int p = i+1; p < literals.length; p++) {
                if (literals[i] == literals[p]) {
                    continue loop;
                }
            }
            temp[n] = literals[i];
            n = n + 1;
        }

        int[] out = new int[n];
        if (n >= 0) System.arraycopy(temp, 0, out, 0, n);
        Arrays.sort(out);
        return out;
    }
}
