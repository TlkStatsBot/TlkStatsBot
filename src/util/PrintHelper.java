package util;

import java.lang.reflect.Field;

public class PrintHelper {

	private static int indenting = 0;
	private static final String NEW_LINE = System.lineSeparator();
	
    public static String toString(Object o) {
      StringBuilder result = new StringBuilder();

      result.append(o.getClass().getName() );
      result.append(String.format(" Object[%d] {", System.identityHashCode(o)) );
      result.append(NEW_LINE);

      Field[] fields = o.getClass().getDeclaredFields();

      for ( Field field : fields  ) {
    	field.setAccessible(true);
        result.append(getIndentation() + "  ");
        try {
          result.append( field.getName() );
          result.append(": ");

          indenting++;
          result.append( field.get(o) );
          indenting--;
        } catch ( IllegalAccessException ex ) {
          System.out.println(ex);
        }
        
        result.append(NEW_LINE);
      }
      result.append(getIndentation() + "}");

      return result.toString();
    }
    
    private static String getIndentation() {
    	StringBuilder identing = new StringBuilder();
    	for (int i=0; i<indenting; i++) {
    		identing.append('\t');
    	}
    	return identing.toString();
    }
    
    public String toString() {
		return PrintHelper.toString(this);
	}
}
