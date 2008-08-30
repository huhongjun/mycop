/*
 * Created on 02.12.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ch.aloba.qiqu.extend;

import ch.aloba.qiqu.librarydata.LibraryManager;
import ch.aloba.qiqu.script.run.QiQuScriptRun;

/**
 * 
 * @author Hansjoerg
 * @version $Revision: 1.1 $
 */
public class TestJava2XMLCommand
{
    public static void main(String[] args) throws Exception
    {
        String[] programargs = new String[] {"-s", "script/testJava2XMLCommand.qiq"};
        LibraryManager.getInstance().loadLibraryInClasspath("src/java/qiqu.library.xml");
        QiQuScriptRun.main(programargs);
    }
}
