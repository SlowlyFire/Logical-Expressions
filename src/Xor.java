// Gal Giladi
package ass4.src;
import java.util.Map;

/**
 * Class Xor creates xor expression and includes some
 * methods we use on boolean expressions with xor operator.
 * @author Gal Giladi
 */
public class Xor extends BinaryExpression {

    /**
     * Constructor Xor - create xor Expression (^).
     * @param e1
     * @param e2
     */
    public Xor(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean leftVal;
        Boolean rightVal;
        leftVal = getLeftExpression().evaluate(assignment);
        rightVal = getRightExpression().evaluate(assignment);
        return (leftVal && !rightVal) || (!leftVal && rightVal);
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression().toString() + " ^ " + this.getRightExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        return new Xor(tmpLeft.assign(var, expression), tmpRight.assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression e1 = getLeftExpression().nandify();
        Expression e2 = getRightExpression().nandify();
        Expression e = new Nand(new Nand(e1, new Nand(e1, e2)), new Nand(e2, new Nand(e1, e2)));
        return e;
    }

    @Override
    public Expression norify() {
        Expression e1 = getLeftExpression().norify();
        Expression e2 = getRightExpression().norify();
        Expression e = new Nor(new Nor(new Nor(e1, e1), new Nor(e2, e2)), new Nor(e1, e2));
        return e;
    }

    @Override
    public Expression simplify() {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        Expression e1 = tmpLeft.simplify();
        Expression e2 = tmpRight.simplify();
        // x ^ 1 = ~(x)
        if (e2.toString().equals("T")) {
            //System.out.println("first1 if xor");
            return new Not(e1).simplify();
        }
        // 1 ^ x = ~(x)
        if (e1.toString().equals("T")) {
            //System.out.println("first2 if xor");
            return new Not(e2).simplify();
        }
        // x ^ 0 = x
        if (e2.toString().equals("F")) {
            //System.out.println("second1 if xor");
            return e1;
        }
        // 0 ^ x = x
        if (e1.toString().equals("F")) {
            //System.out.println("second2 if xor");
            return e2;
        }
        // x ^ x = 0
        if (e1.toString().equals(e2.toString())) {
            //System.out.println("third if xor");
            return new Var("F");
        }

        // else returns the same expression that called simplify
        //System.out.println("outside if xor");
        //System.out.println("left exp in xor: " + e1);
        //System.out.println("right exp in xor: " + e2);
        return new Xor(e1, e2);
    }
}
