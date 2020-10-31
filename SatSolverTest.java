package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SatSolverTest {

    @Test
    void solve() {
        SatMap satMap = SatMap.getInstance();
        Clause[] clauses = new Clause[4];
        clauses[0] = new Clause(new int[]{1, 4, 5});
        clauses[1] = new Clause(new int[]{0, 5, 6});
        clauses[2] = new Clause(new int[]{0, 0, 0});
        clauses[3] = new Clause(new int[]{1, 1, 1});
        assertEquals("Unsatisfiable", satMap.solve(clauses));

        clauses = new Clause[4];
        clauses[0] = new Clause(new int[]{1, 4, 5});
        clauses[1] = new Clause(new int[]{0, 5, 6});
        clauses[2] = new Clause(new int[]{0, 0, 0});
        clauses[3] = new Clause(new int[]{2, 2, 2});
        assertEquals("Satisfiable", satMap.solve(clauses));

        clauses = new Clause[4];
        clauses[0] = new Clause(new int[]{1, 4, 5});
        clauses[1] = new Clause(new int[]{0, 5, 6});
        clauses[2] = new Clause(new int[]{0, 0, 0});
        clauses[3] = new Clause(new int[]{3, 3, 3});
        assertEquals("Satisfiable", satMap.solve(clauses));

        clauses = new Clause[4];
        clauses[0] = new Clause(new int[]{1, 4, 5});
        clauses[1] = new Clause(new int[]{0, 5, 6});
        clauses[2] = new Clause(new int[]{2, 2, 2});
        clauses[3] = new Clause(new int[]{3, 3, 3});
        assertEquals("Unsatisfiable", satMap.solve(clauses));

        clauses = new Clause[4];
        clauses[0] = new Clause(new int[]{1, 4, 5});
        clauses[1] = new Clause(new int[]{10, 5, 6});
        clauses[2] = new Clause(new int[]{110, 0, 0});
        clauses[3] = new Clause(new int[]{1113, 3, 3});
        assertEquals("Satisfiable", satMap.solve(clauses));

        clauses = new Clause[2];
        clauses[0] = new Clause(new int[]{0, 1, 1});
        clauses[1] = new Clause(new int[]{1, 0, 0});
        assertEquals("Satisfiable", satMap.solve(clauses));
    }

    @Test
    void join() {
    }
}