// Gal Giladi
package ass4.src;
import java.util.Map;

/**
 * Class Nand creates nand expression and includes some
 * methods we use on boolean expressions with nand operator.
 * @author Gal Giladi
 */
public class Nand extends BinaryExpression {

    /**
     * Constructor Nand - create nand Expression (A).
     * @param e1
     * @param e2
     */
    public Nand(Expression e1, Expression e2) {
        super(e1, e2);
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean leftVal;
        Boolean rightVal;
        leftVal = getLeftExpression().evaluate(assignment);
        rightVal = getRightExpression().evaluate(assignment);
        return !leftVal || !rightVal;
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExpression().toString() + " A " + this.getRightExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        return new Nand(tmpLeft.assign(var, expression), tmpRight.assign(var, expression));
    }

    @Override
    public Expression nandify() {
        //System.out.println("in nand nandify");
        return new Nand(getLeftExpression().nandify(), getRightExpression().nandify());
    }

    @Override
    public Expression norify() {
        Expression e1 = getLeftExpression().norify();
        Expression e2 = getRightExpression().norify();
        Expression e = new Nor(new Nor(new Nor(e1, e1), new Nor(e2, e2)),
                new Nor(new Nor(e1, e1), new Nor(e2, e2)));
        return e;
    }

    @Override
    public Expression simplify() {
        Expression tmpLeft = getLeftExpression();
        Expression tmpRight = getRightExpression();
        Expression e1 = tmpLeft.simplify();
        Expression e2 = tmpRight.simplify();
        // x A 1 = ~(x)
        if (e2.toString().equals("T")) {
            //System.out.println("first1 if nand");
            //System.out.println(new Not(e1));
            return new Not(e1).simplify();
        }
        // 1 A x = ~(x)
        if (e1.toString().equals("T")) {
            //System.out.println("first2 if nand");
            return new Not(e2).simplify();
        }
        // x A 0 = 1
        if (e2.toString().equals("F")) {
            //System.out.println("second1 if nand");
            return new Var("T");
        }
        // 0 A x = 1
        if (e1.toString().equals("F")) {
            //System.out.println("second2 if nand");
            return new Var("T");
        }
        // x A x = ~(x)
        if (e1.toString().equals(e2.toString())) {
            //System.out.println("third if nand");
            return new Not(e1).simplify();
        }
        // else returns the same expression that called simplify
        //System.out.println("outside if nand");
        //System.out.println("left exp in nand: " + e1);
        //System.out.println("right exp in nand: " + e2);
        return new Nand(e1, e2);
    }
}
