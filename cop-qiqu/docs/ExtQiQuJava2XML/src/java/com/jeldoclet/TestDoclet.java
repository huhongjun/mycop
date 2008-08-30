package com.jeldoclet;
import com.sun.javadoc.*;
import com.sun.tools.javadoc.Main;
import java.io.*;
import java.util.*;
 
public class TestDoclet {
	
  public static void main(String [] arg) {
	  String doclet = "com.jeldoclet.JELDoclet";
	  System.out.println("-------> Test Doclet:"+doclet+" <-------");
	  Main.execute("javadoc",doclet,processOptionsFile("javadoc.options"));
	  
  }  
  
  private static String[] processOptionsFile( final String filename )
  {
      final String options = readOptionsFile( filename );
      final StringTokenizer tokens = new StringTokenizer( options );
      final String[] jargs = new String[tokens.countTokens()];
 
      for ( int i = 0; i < jargs.length; ++i )
      {
          jargs[i] = tokens.nextToken();
      }
      return jargs;
  }
 
  private static String readOptionsFile( final String filename )
  {
      final StringBuffer buffer = new StringBuffer();
      BufferedReader br = null;
      try
      {
          br = new BufferedReader( new FileReader( filename ) );
          String line;
          while ( (line = br.readLine()) != null )
          {
              buffer.append( line ).append( "\n" );
          }
      }
      catch ( final IOException ioe )
      {
          ioe.printStackTrace();
          buffer.setLength( 0 );
      }
      finally
      {
          if ( br != null )
          {
              try
              {
                  br.close();
              }
              catch ( IOException ioe )
              {
                  ioe.printStackTrace();
              }
          }
      }
 
      return buffer.toString();
  }
}
