// Gal Giladi
package ass4.src;

/**
 * Class Not creates not expression and includes some
 * methods we use on boolean expressions with not operator.
 * @author Gal Giladi
 */
public class Not extends UnaryExpression {

    /**
     * Constructor Not - create not Expression (~).
     * @param e
     */
    public Not(Expression e) {
        super(e);
    }
}
