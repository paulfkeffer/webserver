package myHTTPServerStuff;
import java.io.*;
import java.net.*;
import java.util.*;

import com.sun.net.httpserver.*;
public class EchoGetHandler implements com.sun.net.httpserver.HttpHandler {

         @Override

         public void handle(HttpExchange he) throws IOException {
                 // parse request
                 Map<String, Object> parameters = new HashMap<String, Object>();
                 URI requestedUri = he.getRequestURI();
                 String query = requestedUri.getRawQuery();
                 parseQuery(query, parameters);

                 // send response
                 String response = "";
                 for (String key : parameters.keySet())
                          response += key + " = " + parameters.get(key) + "\n";
                 he.sendResponseHeaders(200, response.length());
                 OutputStream os = he.getResponseBody();
                 os.write(response.toString().getBytes());

                 os.close();
         }
         
         
         public static void parseQuery(String query, Map<String, 
        			Object> parameters) throws UnsupportedEncodingException {

        		         if (query != null) {
        		                 String pairs[] = query.split("[&]");
        		                 for (String pair : pairs) {
        		                          String param[] = pair.split("[=]");
        		                          String key = null;
        		                          String value = null;
        		                          if (param.length > 0) {
        		                          key = URLDecoder.decode(param[0], 
        		                          	System.getProperty("file.encoding"));
        		                          }

        		                          if (param.length > 1) {
        		                                   value = URLDecoder.decode(param[1], 
        		                                   System.getProperty("file.encoding"));
        		                          }

        		                          if (parameters.containsKey(key)) {
        		                                   Object obj = parameters.get(key);
        		                                   if (obj instanceof List<?>) {
        		                                            @SuppressWarnings("unchecked")
															List<String> values = (List<String>) obj;
        		                                            values.add(value);

        		                                   } else if (obj instanceof String) {
        		                                            List<String> values = new ArrayList<String>();
        		                                            values.add((String) obj);
        		                                            values.add(value);
        		                                            parameters.put(key, values);
        		                                   }
        		                          } else {
        		                                   parameters.put(key, value);
        		                          }
        		                 }
        		         }
        		}
         
}