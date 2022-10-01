// Gal Giladi
package ass4.src;
import java.util.Map;

/**
 * Class And creates and expression and includes some
 * methods we use on boolean expressions with and operator.
 * @author Gal Giladi
 */
public class And extends BinaryExpression {

    /**
     * Constructor And - create And Expression (&).
     * @param e1
     * @param e2
     */
    public And(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean leftVal;
        Boolean rightVal;
        leftVal = getLeftExpression().evaluate(assignment);
        rightVal = getRightExpression().evaluate(assignment);
        return leftVal & rightVal;
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression().toString() + " & " + this.getRightExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        return new And(tmpLeft.assign(var, expression), tmpRight.assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression e1 = getLeftExpression().nandify();
        Expression e2 = getRightExpression().nandify();
        Expression e = new Nand(new Nand(e1, e2), new Nand(e1, e2));
        return e;
    }

    @Override
    public Expression norify() {
        Expression e1 = getLeftExpression().norify();
        Expression e2 = getRightExpression().norify();
        Expression e = new Nor(new Nor(e1, e1), new Nor(e2, e2));
        return e;
    }

    @Override
    public Expression simplify() {
        Expression e1 = getLeftExpression().simplify();
        Expression e2 = getRightExpression().simplify();

        // x & 1 = x
        if (e2.toString().equals("T")) {
            //System.out.println("first1 if and");
            return e1;
        }
        // 1 & x = x
        if (e1.toString().equals("T")) {
            //System.out.println("first2 if and");
            return e2;
        }
        // x & 0 = 0
        if (e2.toString().equals("F")) {
            //System.out.println("second1 if and");
            return e2;
        }
        // 0 & x = 0
        if (e1.toString().equals("F")) {
            //System.out.println("second2 if and");
            return e1;
        }
        // x & x = x
        if (e1.toString().equals(e2.toString())) {
            //System.out.println("third if and");
            return e1;
        }
        // else returns the same expression that called simplify
        //System.out.println("outside if and");
        //System.out.println("left exp in and: " + e1);
        //System.out.println("right exp in and: " + e2);
        return new And(e1, e2);
    }
}


