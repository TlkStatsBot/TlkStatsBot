package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Update;
import util.HibernateFix;

public class TlkStatsBot {

    private static final String URL_FORMAT = "https://api.telegram.org/bot%s/%s";
	
    private static final int DEFAULT_UPDATE_INTERVAL_MS = 10000;
    private static final int POOL_SIZE = 1;
    
    private class UpdateTask implements Runnable { 
    	
    	int lastUpdateId = -1;

    	public void run() { 	
    		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("local_h2_persistence");
    		EntityManager entityManager = entityManagerFactory.createEntityManager();
    		EntityTransaction t = entityManager.getTransaction();
    		
            try {
            	String offsetParam = lastUpdateId > 0 ? "?offset=" + lastUpdateId : "";
        		Update[] updates = telegramBotAPI("getUpdates" + offsetParam, Update[].class);
                
                for (Update u : updates) {
                	try {
	                	t.begin();
	                	HibernateFix.fixObject(u); 
	                	entityManager.merge(u);
	                	t.commit();
                	} catch (Exception e) {
                		t.rollback();
                    	e.printStackTrace();
                    }
                } 
            } catch (Throwable e) {
            	// TODO: Save json String to a file, so that the error can be reproduced and fixed later.
                e.printStackTrace();
                System.err.println("EXCEPTION");
                System.err.flush();
        	} finally {
        		if (entityManager != null) {
        			entityManager.close();
        		}
        		
        		if (entityManagerFactory != null) {
        			entityManagerFactory.close();
        		}
        		executor.schedule(this, DEFAULT_UPDATE_INTERVAL_MS, TimeUnit.MILLISECONDS);
        	}
		}
    }
    
    private final String token;
    private final UpdateTask updateTask = new UpdateTask();
    
	private final ThreadLocal<ObjectMapper> mapper = new ThreadLocal<ObjectMapper>() {
    	protected ObjectMapper initialValue() {
    		return new ObjectMapper();
    	};
    };
    
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(POOL_SIZE);
    
    private int updateInterval = DEFAULT_UPDATE_INTERVAL_MS;
    
    public TlkStatsBot(String token) {
    	this.token = token;
    	try {
    		testToken();
    	} catch (Exception e) {
    		throw new RuntimeException("Error testing bot token", e);
    	}
    	
    	executor.schedule(updateTask, DEFAULT_UPDATE_INTERVAL_MS, TimeUnit.MILLISECONDS);
    }
    
    public int getUpdateInterval() {
		return updateInterval;
	}

	public void setUpdateInterval(int updateInterval) {
		this.updateInterval = updateInterval;
	}

    private void testToken() throws IOException {
    	String json = telegramBotAPI("getMe");
    	mapper.get().readTree(json);
    	System.out.println(json);
    }
    
    private <T> T telegramBotAPI(String request, Class<T> clazz) throws IOException {
    	String json = telegramBotAPI(request);
		JsonNode node = mapper.get().readTree(json);
		
		// TODO: Check result and throw exception if bad code.
		JsonNode result = node.get("result");
		return mapper.get().convertValue(result, clazz);
    }
    
    private String telegramBotAPI(String request) throws IOException {
        return getHTML(String.format(URL_FORMAT, token, request));
    }
    
    private static String getHTML(String urlToRead) throws IOException {
        StringBuilder result = new StringBuilder();

    	URL url = new URL(urlToRead);
    	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int statusCode = ((HttpURLConnection) conn).getResponseCode();
        InputStream iStream = statusCode == 200 ? conn.getInputStream() : conn.getErrorStream();
        
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(iStream))) {
        	String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }

        String response = result.toString();      
        return response;
    }
}
