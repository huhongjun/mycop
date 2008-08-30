/*
 * Created on 20.01.2006
 */
package ch.aloba.qiqu.extend;

import ch.aloba.qiqu.parser.model.Expression;
import ch.aloba.qiqu.script.base.BaseFunction;
import ch.aloba.qiqu.script.base.IBaseFunction;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple QiQu-Function.
 * 
 * @author Hansjoerg
 * @version $Revision: $
 */
public class Greeting extends BaseFunction
{

    /**
     * @see ch.aloba.qiqu.script.base.BaseFunction#getFunctionName()
     */
    public String getFunctionName()
    {
        return "greeting";
    }

    /**
     * @see ch.aloba.qiqu.script.base.BaseFunction#getFunctionGroup()
     */
    public String getFunctionGroup()
    {
        return "text";
    }
    
    /**
     * @see ch.aloba.qiqu.script.base.BaseFunction#getDescription()
     */
    public String getDescription()
    {
        return "Creates a greeting string depending on the sex and name of a person.";
    }

    /**
     * @see ch.aloba.qiqu.script.base.BaseFunction#getReturnType()
     */
    public int getReturnType()
    {
        return IBaseFunction.TYPE_TEXT;
    }

    /**
     * @see ch.aloba.qiqu.script.base.BaseFunction#getNumberOfParameters()
     */
    public int getNumberOfParameters()
    {
        return 2;
    }

    /**
     * @see ch.aloba.qiqu.script.base.BaseFunction#isLastParameterMultiple()
     */
    public boolean isLastParameterMultiple()
    {
        return false;
    }

    /**
     * @see ch.aloba.qiqu.script.base.BaseFunction#setParameters()
     */
    protected void setParameters()
    {
        addParameter("1", "The sex of th person, either 'm' or 'f'");
        addParameter("2", "The name of the person.");
    }
    
    
    /**
     * @see ch.aloba.qiqu.script.base.BaseFunction#evaluateFunction(java.lang.String[], ch.aloba.qiqu.parser.model.Expression[])
     */
    public String evaluateFunction(String[] params, Expression[] expressions) throws Exception
    {
        StringBuffer greetStr = new StringBuffer("Hello ");
        if (params[0].equals("m"))
        {
            greetStr.append("Mr.");
        }
        else if (params[0].equals("f"))
        {
            greetStr.append("Ms.");           
        }
        greetStr.append(" ").append(params[1]);
        return greetStr.toString();
    }
}
