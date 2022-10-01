// Gal Giladi
package ass4.src;
import java.util.List;
import java.util.Map;
/**
 * Abstract Class BaseExpression creates an expression and includes some
 * methods we use on boolean expressions with some operators.
 * @author Gal Giladi
 */
public abstract class BaseExpression implements Expression {

    /**
     * @return Boolean value
     * @throws Exception
     * A convenience method. Like the `evaluate(assignment)` method above,
     *      but uses an empty assignment.
     */
    public Boolean evaluate() throws Exception {
        throw new Exception("Expression has no assigned value");
    }

    /**
     * @param assignment which is a Map type
     * @return Boolean value
     * @throws Exception
     * Evaluate the expression using the variable values provided
     *      in the assignment, and return the result. If the expression
     *      contains a variable which is not in the assignment, an exception
     *      is thrown.
     */
    public abstract Boolean evaluate(Map<String, Boolean> assignment) throws Exception;

    /**
     * @return List of Strings
     * Returns a list of the variables in the expression.
     */
    public abstract List<String> getVariables();

    /**
     * @return String type
     * Returns a nice string representation of the expression.
     */
    public abstract String toString();

    /**
     * @param var which is a String
     * @param expression which is an Expression
     * @return Expression type
     *  Returns a new expression in which all occurrences of the variable
     *      var are replaced with the provided expression (Does not modify the
     *      current expression).
     */
    public abstract Expression assign(String var, Expression expression);

    /**
     * @return Expression type
     * Returns the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    public abstract Expression nandify();

    /**
     * @return Expression type
     *      Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    public abstract Expression norify();

    /**
     *
     * @return Expression type
     * Returned a simplified version of the current expression.
     */
    public abstract Expression simplify();
}
