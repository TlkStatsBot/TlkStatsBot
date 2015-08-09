package model;

import java.lang.reflect.Field;

public class PrintHelper {

    public static String toString(Object o) {
      StringBuilder result = new StringBuilder();
      String newLine = System.getProperty("line.separator");

      result.append( o.getClass().getName() );
      result.append( " Object {" );
      result.append(newLine);

      //determine fields declared in this class only (no fields of superclass)
      Field[] fields = o.getClass().getDeclaredFields();

      //print field names paired with their values
      for ( Field field : fields  ) {
    	field.setAccessible(true);
        result.append("  ");
        try {
          result.append( field.getName() );
          result.append(": ");
          //requires access to private field:
          result.append( field.get(o) );
        } catch ( IllegalAccessException ex ) {
          System.out.println(ex);
        }
        result.append(newLine);
      }
      result.append("}");

      return result.toString();
    }
    
    public String toString() {
		return PrintHelper.toString(this);
	}
}
