// Gal Giladi
package ass4.src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Abstract Class BinaryExpression creates a binary expression and includes some
 * methods we use on boolean expressions with some operators.
 * @author Gal Giladi
 */
public abstract class BinaryExpression extends BaseExpression  {
    private Expression leftExpression;
    private Expression rightExpression;

    /**
     * Constructor BinaryExpression - creates binary expression (x op x).
     * @param e1
     * @param e2
     */
    protected BinaryExpression(Expression e1, Expression e2) {
        this.leftExpression = e1;
        this.rightExpression = e2;
    }

    /**
     * get the left expression from the whole expression.
     * @return Expression type
     */
    protected Expression getLeftExpression() {
        return leftExpression;
    }

    /**
     * get the right expression from the whole expression.
     * @return Expression type
     */
    protected Expression getRightExpression() {
        return rightExpression;
    }

    @Override
    public List<String> getVariables() {
        List<String> list1 = leftExpression.getVariables();
        List<String> list2 = rightExpression.getVariables();
        List<String> listAll = new ArrayList<>();
        for (String v : list1) {
            if (!listAll.contains(v)) {
                listAll.add(v);
            }
        }

        for (String v : list2) {
            if (!listAll.contains(v)) {
                listAll.add(v);
            }
        }
        //list1.addAll(list2);
        return listAll;
    }
    @Override
    public abstract Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    @Override
    public abstract String toString();

    @Override
    public abstract Expression assign(String var, Expression expression);

    @Override
    public abstract Expression nandify();

    @Override
    public abstract Expression norify();

    @Override
    public abstract Expression simplify();
}
