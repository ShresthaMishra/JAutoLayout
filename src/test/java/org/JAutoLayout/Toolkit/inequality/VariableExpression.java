package org.JAutoLayout.Toolkit.inequality;

import org.JAutoLayout.Toolkit.*;
import org.junit.Test;

import org.JAutoLayout.Toolkit.ConstraintException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by alex on 31/01/16.
 */
public class VariableExpression {
    private static double EPSILON = 1.0e-8;

    @Test
    public void lessThanEqualTo() throws Exception {
        Variable x = new Variable("x");
        Solver solver = new Solver();
        solver.addConstraint(Operations.lessThanOrEqualTo(x, new Expression(100)));
        solver.updateVariables();
        assertTrue(x.getValue() <= 100);
        solver.addConstraint(Operations.equals(x, 90));
        solver.updateVariables();
        assertEquals(x.getValue(), 90, EPSILON);
    }

    @Test(expected = ConstraintException.class)
    public void lessThanEqualToUnsatisfiable() throws Exception {
        Variable x = new Variable("x");
        Solver solver = new Solver();
        solver.addConstraint(Operations.lessThanOrEqualTo(x, new Expression(100)));
        solver.updateVariables();
        assertTrue(x.getValue() <= 100);
        solver.addConstraint(Operations.equals(x, 110));
        solver.updateVariables();
    }

    @Test
    public void greaterThanEqualTo() throws Exception {
        Variable x = new Variable("x");
        Solver solver = new Solver();
        solver.addConstraint(Operations.greaterThanOrEqualTo(x, new Expression(100)));
        solver.updateVariables();
        assertTrue(x.getValue() >= 100);
        solver.addConstraint(Operations.equals(x, 110));
        solver.updateVariables();
        assertEquals(x.getValue(), 110, EPSILON);
    }

    @Test(expected = ConstraintException.class)
    public void greaterThanEqualToUnsatisfiable() throws Exception {
        Variable x = new Variable("x");
        Solver solver = new Solver();
        solver.addConstraint(Operations.greaterThanOrEqualTo(x, 100));
        solver.updateVariables();
        assertTrue(x.getValue() >= 100);
        solver.addConstraint(Operations.equals(x, 90));
        solver.updateVariables();
    }
}
