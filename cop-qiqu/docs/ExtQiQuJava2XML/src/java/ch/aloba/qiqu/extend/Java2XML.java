/*
 * Created on 07.01.2006
 */
package ch.aloba.qiqu.extend;

import java.util.StringTokenizer;

import com.sun.tools.javadoc.Main;

import ch.aloba.qiqu.script.base.BaseCommand;
import ch.aloba.qiqu.script.base.IBaseCommand;
import ch.aloba.qiqu.script.base.cmdparameter.CmdParamText;
import ch.aloba.qiqu.script.exception.CommandException;

/**
 * A simple command that writes to the output.
 * 
 * @author Hansjoerg
 * @version $Revision: $
 */
public class Java2XML extends BaseCommand
{
    private CmdParamText m_outputParam = null;
    private CmdParamText m_sourceParam = null;
    
    /**
     * @see ch.aloba.qiqu.script.base.BaseCommand#getCommandName()
     */
    public String getCommandName()
    {
        return "Java2XML";
    }
    
    /**
     * @see ch.aloba.qiqu.script.base.BaseCommand#getDescription()
     */
    public String getDescription()
    {
        return "Writes the content of the attribute 'output' to the console.";
    }

    /**
     * @see ch.aloba.qiqu.script.base.BaseCommand#getCommandGroup()
     */
    public String getCommandGroup()
    {
        return "Logging";
    }

    /**
     * @see ch.aloba.qiqu.script.base.BaseCommand#isUsingSubCommand()
     */
    public boolean isUsingSubCommand()
    {
        return false;
    }

    /**
     * @see ch.aloba.qiqu.script.base.BaseCommand#setParameters()
     */
    protected void setParameters()
    { 
        m_sourceParam = addTextParameter("sourceFile", "Java source");
        m_outputParam = addTextParameter("outputPath", "XML output path");
        addParameterCombination(m_sourceParam,m_outputParam);        
    }

    /**
     * @see ch.aloba.qiqu.script.base.IBaseCommand#doIt()
     */
    public int doIt() throws CommandException
    {
        System.out.println("Output file name: " + m_outputParam.getValue());
        
  	  	String doclet = "com.jeldoclet.JELDoclet";

  	  	final String options = "-docletpath .\\dist\\java2xmllib.jar -d "+ m_outputParam.getValue()
  	  			+ " -multiple -private " + m_sourceParam.getValue();
  	  	
        final StringTokenizer tokens = new StringTokenizer( options );
        final String[] jargs = new String[tokens.countTokens()];
   
        for ( int i = 0; i < jargs.length; ++i )
        {
            jargs[i] = tokens.nextToken();
        }
  	  	
  	  	System.out.println("-------> Test Doclet:"+doclet+" <-------");
  	  	System.out.println(System.getProperty("classpath","a"));
  	  	Main.execute("javadoc",doclet,jargs );

        
        
        return IBaseCommand.COMMAND_RETURN_TYPE_NO_LOOP;
    }
}
