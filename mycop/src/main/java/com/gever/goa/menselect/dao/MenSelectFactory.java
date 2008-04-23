package com.gever.goa.menselect.dao;

public abstract class MenSelectFactory {
    private static String className = "com.gever.goa.menselect.dao.imp.DefaultMenSelectFactory";
    private static MenSelectFactory factory = null;
    public MenSelectFactory() {
    }

    /**
@return com.gever.sysman.privilege.dao.PrivilegeFactory
@roseuid 40BAB9B00167
*/
public static synchronized MenSelectFactory getInstance() {
   if (factory == null) {
       try {
           Class c = Class.forName(className);
           factory = (MenSelectFactory) c.newInstance();
       } catch (Exception e) {
           System.err.println("Failed to load PrivilegeFactory class " +
               className);
           e.printStackTrace();

           return null;
       }
   }

   return factory;
}
public abstract MenSelectDAO createMenSelect(String dbData);

}
