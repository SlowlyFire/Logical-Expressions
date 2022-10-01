// Gal Giladi
package ass4.src;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class Val creates val expression and includes some
 * methods we use on boolean expressions with val true or false.
 * @author Gal Giladi
 */
public class Val implements Expression {
    private Boolean val;

    /**
     * Constructor Val - create Val Expression (T/F).
     * @param newVal
     */
    public Val(Boolean newVal) {
        this.val = newVal;
    }

    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        // immediately returns true or false because its val (true,false)
        assignment.put("T", true);
        assignment.put("F", false);
        assignment.put("true", true);
        assignment.put("false", false);
        return assignment.get(this.val.toString());
    }

    @Override
    public Boolean evaluate() throws Exception {
        return this.val;
    }

    @Override
    public List<String> getVariables() {
        List<String> list = new ArrayList<>();
        return list;
    }

    @Override
    public String toString() {
        if (this.val.toString().equals("true")) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.val.toString().equals(var)) {
            return expression;
        } else {
            return new Val(this.val);
        }
    }

    @Override
    public Expression nandify() {
        return new Val(this.val);
    }

    @Override
    public Expression norify() {
        return new Val(this.val);
    }

    @Override
    public Expression simplify() {
        //System.out.println("simplify in val: " + this.val);
        return new Val(this.val);
    }
}
