// Gal Giladi
package ass4.src;
import java.util.List;
import java.util.Map;

/**
 * interface Expression includes some methods we use on boolean expressions.
 * @author Gal Giladi
 */
public interface Expression {

    /**
     * @param assignment which is a Map type
     * @return Boolean value
     * @throws Exception
     * Evaluate the expression using the variable values provided
     *      in the assignment, and return the result. If the expression
     *      contains a variable which is not in the assignment, an exception
     *      is thrown.
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;


    /**
     * @return Boolean value
     * @throws Exception
     * A convenience method. Like the `evaluate(assignment)` method above,
     *      but uses an empty assignment.
     */
    Boolean evaluate() throws Exception;


    /**
     * @return List of Strings
     * Returns a list of the variables in the expression.
     */
    List<String> getVariables();


    /**
     * @return String type
     * Returns a nice string representation of the expression.
     */
    String toString();


    /**
     * @param var which is a String
     * @param expression which is an Expression
     * @return Expression type
     *  Returns a new expression in which all occurrences of the variable
     *      var are replaced with the provided expression (Does not modify the
     *      current expression).
     */
    Expression assign(String var, Expression expression);

    /**
     * @return Expression type
     * Returns the expression tree resulting from converting all the operations to the logical Nand operation.
     */
    Expression nandify();

    /**
     * @return Expression type
     *      Returns the expression tree resulting from converting all the operations to the logical Nor operation.
     */
    Expression norify();


    /**
     *
     * @return Expression type
     * Returned a simplified version of the current expression.
     */
    Expression simplify();
}
