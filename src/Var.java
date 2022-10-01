// Gal Giladi
package ass4.src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class Var creates var expression and includes some
 * methods we use on boolean expressions with variables (x,y,z etc.).
 * @author Gal Giladi
 */
public class Var implements Expression {
    private String var;

    /**
     * Constructor Var - create Var Expression (x,y,z etc.).
     * @param var
     */
    public Var(String var) {
        this.var = var;
    }

    /**
     * get the variable from the whole expression.
     * @return String type
     */
    public String getVar() {
        return this.var;
    }

    /**
     * set a variable to the whole expression.
     * @param newVar
     */
    public void setVar(String newVar) {
        this.var = newVar;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        // the get method looks for the value of the key var, and returns it.
        // returns a Boolean type, according to the map - String is the key, Boolean is the value.
        return assignment.get(this.var);
    }

    @Override
    public Boolean evaluate() throws Exception {
        throw new Exception("Expression has no assigned value");
    }

    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        list.add(this.var);
        return list;
    }

    @Override
    public String toString() {
        return this.var;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.var.equals(var)) {
            return expression;
        } else {
            return new Var(this.var);
        }
    }

    @Override
    public Expression nandify() {
        return new Var(this.var);

    }

    @Override
    public Expression norify() {
        return new Var(this.var);
    }

    @Override
    public Expression simplify() {
        //System.out.println("simplify in var: " + this.var);
        return new Var(this.var);
    }
}
