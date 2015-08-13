package util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Set;

public class HibernateFix {
	public static void fixObject(Object o) {
    	fixObject(o, new HashSet<Object>());
    }
    
    private static void fixObject(Object o, Set<Object> objs) {
    	for (Field f : o.getClass().getDeclaredFields()) {
    		if (!Modifier.isStatic(f.getModifiers()) && 
    			!Modifier.isFinal(f.getModifiers()) &&
    			!Modifier.isTransient(f.getModifiers())) {
	    		try {
	    			f.setAccessible(true);
	    			Object fieldValue = f.get(o);
	    			if (fieldValue != null) {
		    			if (objs.contains(fieldValue)) {
		    				Object firstValue = getSimilar(objs, fieldValue);
		    				f.set(o, firstValue);
		    			} else {
		    				if (!f.getType().isPrimitive()) {
		    					fixObject(fieldValue, objs);
		    				}
		    				objs.add(fieldValue);
		    			}
	    			}
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}  	
    		}
    	}
    }
    
    private static Object getSimilar(Set<Object> set, Object key) {
    	for (Object o : set) {
    		if (o.equals(key)) {
    			return o;
    		}
    	}
    	
    	throw new IllegalStateException("Not found!");
    }
}
