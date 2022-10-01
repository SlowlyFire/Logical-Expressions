// Gal Giladi
package ass4.src;
import java.util.Map;

/**
 * Class Nor creates nor expression and includes some
 * methods we use on boolean expressions with nor operator.
 * @author Gal Giladi
 */
public class Nor extends BinaryExpression {

    /**
     * Constructor Nor - create nor Expression (A).
     * @param e1
     * @param e2
     */
    public Nor(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean leftVal;
        Boolean rightVal;
        leftVal = getLeftExpression().evaluate(assignment);
        rightVal = getRightExpression().evaluate(assignment);
        return !leftVal && !rightVal;
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression().toString() + " V " + this.getRightExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        return new Nor(tmpLeft.assign(var, expression), tmpRight.assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression e1 = getLeftExpression().nandify();
        Expression e2 = getRightExpression().nandify();
        Expression e = new Nand(new Nand(new Nand(e1, e1), new Nand(e2, e2)),
                new Nand(new Nand(e1, e1), new Nand(e2, e2)));
        return e;
    }

    @Override
    public Expression norify() {
        return new Nor(getLeftExpression().norify(), getRightExpression().norify());
    }

    @Override
    public Expression simplify() {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        Expression e1 = tmpLeft.simplify();
        Expression e2 = tmpRight.simplify();
        // x V 1 = 0
        if (e2.toString().equals("T")) {
            //System.out.println("first1 if nor");
            return new Var("F");
        }
        // 1 V x = 0
        if (e1.toString().equals("T")) {
            //System.out.println("first2 if nor");
            return new Var("F");
        }
        // x V 0 = ~(x)
        if (e2.toString().equals("F")) {
            //System.out.println("second1 if nor");
            return new Not(e1).simplify();
        }
        // 0 V x = ~(x)
        if (e1.toString().equals("F")) {
            //System.out.println("second2 if nor");
            return new Not(e2).simplify();
        }
        // x V x = ~(x)
        if (e1.toString().equals(e2.toString())) {
            return new Not(e1).simplify();
        }
        // else returns the same expression that called simplify
        //System.out.println("outside if nor");
        //System.out.println("left exp in nor: " + e1);
        //System.out.println("right exp in nor: " + e2);
        return new Nor(e1, e2);
    }


}

