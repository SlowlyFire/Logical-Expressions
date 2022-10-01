// Gal Giladi
package ass4.src;
import java.util.List;
import java.util.Map;

/**
 * Abstract Class UnaryExpression creates a unary expression and includes some
 * methods we use on boolean expressions with some operators.
 * @author Gal Giladi
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression e;

    /**
     * Constructor UnaryExpression - create unary expression (op(x)).
     * @param e
     */
    protected UnaryExpression(Expression e) {
        this.e = e;
    }

    /**
     * get the expression.
     * @return Expression type
     */
    protected Expression getExpression() {
        return this.e;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        Boolean val;
        val = this.e.evaluate(assignment);
        return !val;
    }

    @Override
    public List<String> getVariables() {
        List<String> list = e.getVariables();
        return list;
    }

    @Override
    public String toString() {
        return "~(" + getExpression().toString() + ")";
    }

    @Override
    public Expression assign(String var, Expression expression) {
        Expression tmp = this.e;
        return new Not(tmp.assign(var, expression));
    }

    @Override
    public Expression nandify() {
        return new Nand(this.e.nandify(), this.e.nandify());
    }

    @Override
    public Expression norify() {
        return new Nor(this.e.norify(), this.e.norify());
    }

    @Override
    public Expression simplify() {
        //System.out.println("in not in general");
        e = e.simplify();
        // ~(val)
        if (e.toString().equals("T")) {
            return new Var("F");
        }
        if (e.toString().equals("F")) {
            //System.out.println("in not if");
            return new Var("T");
        }
        return new Not(e);
    }
}
