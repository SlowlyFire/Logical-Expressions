// Gal Giladi
package ass4.src;
import java.util.Map;

/**
 * Class Or creates or expression and includes some
 * methods we use on boolean expressions with or operator.
 * @author Gal Giladi
 */
public class Or extends BinaryExpression {

    /**
     * Constructor Or - create or Expression (|).
     * @param e1
     * @param e2
     */
    public Or(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean leftVal;
        Boolean rightVal;
        leftVal = getLeftExpression().evaluate(assignment);
        rightVal = getRightExpression().evaluate(assignment);
        return leftVal | rightVal;
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression().toString() + " | " + this.getRightExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        return new Or(tmpLeft.assign(var, expression), tmpRight.assign(var, expression));
    }

    @Override
    public Expression nandify() {
        Expression e1 = getLeftExpression().nandify();
        Expression e2 = getRightExpression().nandify();
        Expression e = new Nand(new Nand(e1, e1), new Nand(e2, e2));
        return e;
    }

    @Override
    public Expression norify() {
        Expression e1 = getLeftExpression().norify();
        Expression e2 = getRightExpression().norify();
        Expression e = new Nor(new Nor(e1, e2), new Nor(e1, e2));
        return e;
    }

    @Override
    public Expression simplify() {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        Expression e1 = tmpLeft.simplify();
        Expression e2 = tmpRight.simplify();
        // x | 1 = 1
        if (e2.toString().equals("T")) {
            //System.out.println("first1 if or");
            return e2;
        }
        // 1 | x = 1
        if (e1.toString().equals("T")) {
            //System.out.println("first2 if or");
            return e1;
        }
        // x | 0 = x
        if (e2.toString().equals("F")) {
            //System.out.println("second1 if or");
            return e1;
        }
        // 0 | x = x
        if (e1.toString().equals("F")) {
            //System.out.println("second2 if or");
            return e2;
        }
        // x | x = x
        if (e1.toString().equals(e2.toString())) {
            //System.out.println("third if or");
            return e1;
        }
        // else returns the same expression that called simplify
        //System.out.println("outside if or");
        //System.out.println("left exp in or: " + e1);
        //System.out.println("right exp in or: " + e2);
        return new Or(e1, e2);
    }
}
