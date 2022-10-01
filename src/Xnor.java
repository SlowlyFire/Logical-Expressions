// Gal Giladi
package ass4.src;
import java.util.Map;

/**
 * Class Xnor creates xnor expression and includes some
 * methods we use on boolean expressions with xnor operator.
 * @author Gal Giladi
 */
public class Xnor extends BinaryExpression {

    /**
     * Constructor Xnor - create xnor Expression (#).
     * @param e1
     * @param e2
     */
    public Xnor(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean leftVal;
        Boolean rightVal;
        leftVal = getLeftExpression().evaluate(assignment);
        rightVal = getRightExpression().evaluate(assignment);
        return leftVal == rightVal;
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression().toString() + " # " + this.getRightExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        return new Xnor(tmpLeft.assign(var, expression), tmpRight.assign(var, expression));
    }

    @Override
    public Expression nandify() {
        //System.out.println("in xnor nandify");
        Expression e1 = getLeftExpression().nandify();
        Expression e2 = getRightExpression().nandify();
        Expression e = new Nand(new Nand(new Nand(e1, e1), new Nand(e2, e2)), new Nand(e1, e2));
        return e;
    }

    @Override
    public Expression norify() {
        Expression e1 = getLeftExpression().norify();
        Expression e2 = getRightExpression().norify();
        Expression e = new Nor(new Nor(e1, new Nor(e1, e2)), new Nor(e2, new Nor(e1, e2)));
        return e;
    }

    @Override
    public Expression simplify() {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        Expression e1 = tmpLeft.simplify();
        Expression e2 = tmpRight.simplify();
        // x # x = 1
        if (e1.toString().equals(e2.toString())) {
            //System.out.println("first and only xnor if");
            return new Var("T");
        }
        // F # T = 0
        if (e1.toString() == "F" && e2.toString() == "T") {
            return new Var("F");
        }
        // T # F = 1
        if (e1.toString() == "T" && e2.toString() == "F") {
            return new Var("F");
        }
        // else returns the same expression that called simplify
        //System.out.println("outside if xnor");
        //System.out.println("left exp in xnor: " + e1);
        //System.out.println("right exp in xnor: " + e2);
        return new Xnor(e1, e2);
    }
}
