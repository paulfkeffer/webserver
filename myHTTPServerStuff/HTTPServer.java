package myHTTPServerStuff;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.*;
public class HTTPServer {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		if (args.length < 2) {
			throw new Exception("This program requires exactly two arguments. Please provide the port number followed by the root path.");
		}
		int port = Integer.parseInt(args[0]);
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		System.out.println("server started at " + port);
		server.createContext("/", new RootHandler(args[1]));
		server.createContext("/echoHeader", new EchoHeaderHandler());
		server.createContext("/echoGet", new EchoGetHandler());
		server.createContext("/echoPost", new EchoPostHandler());
		server.setExecutor(null);
		server.start();
	}

}
