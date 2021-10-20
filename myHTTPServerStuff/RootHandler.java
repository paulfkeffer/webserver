package myHTTPServerStuff;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

import com.sun.net.httpserver.*;
public class RootHandler implements com.sun.net.httpserver.HttpHandler {
	String htpath;

	public RootHandler(String htpath) {
		this.htpath = htpath;
	}

	public void handle(HttpExchange he) throws IOException {
		String path = he.getRequestURI().getPath();
		File file = new File(htpath + path);
		ArrayList<String> headervalue = new ArrayList<String>();
		ArrayList<String> headervalue2 = new ArrayList<String>();
		headervalue.add("PaulKeffersServer");
		he.getResponseHeaders().put("Server", headervalue);
		if (file.getAbsoluteFile().exists()) {
			if (file.isDirectory()) {
				File file2 = new File(htpath + path + "/index.html");
				File file3 = new File(htpath + path + "/index.htm");
				File file4 = new File(htpath + path + "/index.php");
				if (file2.exists()) {
					headervalue2.add("text/html");
					he.getResponseHeaders().put("Content-Type", headervalue2);
					Files.readAllBytes(file2.toPath());
					he.sendResponseHeaders(200, file2.length());
					OutputStream os = he.getResponseBody();
					os.write(Files.readAllBytes(file2.toPath()));
					os.close();
				}
				else if (file3.exists()) {
					headervalue2.add("text/html");
					he.getResponseHeaders().put("Content-Type", headervalue2);
					Files.readAllBytes(file3.toPath());
					he.sendResponseHeaders(200, file3.length());
					OutputStream os = he.getResponseBody();
					os.write(Files.readAllBytes(file3.toPath()));
					os.close();
				}
				else if (file4.exists()) {
					headervalue2.add("text/html");
					he.getResponseHeaders().put("Content-Type", headervalue2);
					Files.readAllBytes(file4.toPath());
					he.sendResponseHeaders(200, file4.length());
					OutputStream os = he.getResponseBody();
					os.write(Files.readAllBytes(file4.toPath()));
					os.close();
				}
				else {
					headervalue2.add("text/html");
					he.getResponseHeaders().put("Content-Type", headervalue2);
					String response = "<title>404 Not Found</title> 404 not found :( please try a different path.";
					OutputStream os = he.getResponseBody();
					he.sendResponseHeaders(404, file.length());
					os.write(response.getBytes());
					os.close();
				}
			}
			else {
				String fullpath = file.getPath();
				String extension = fullpath.substring(fullpath.lastIndexOf(".")+1);
				if (extension.equals("html") || extension.contains("htm") || extension.equals("php")) {
					headervalue2.add("text/html");
				}
				else if (extension.equals("css")) {
					headervalue2.add("text/css");
				}
				else if (extension.equals("jpeg") || extension.equals("jpg") || extension.equals("png")) {
					headervalue2.add("image/" + extension);
				}
				he.getResponseHeaders().put("Content-Type", headervalue2);
				Files.readAllBytes(file.toPath());
				he.sendResponseHeaders(200, file.length());
				OutputStream os = he.getResponseBody();
				os.write(Files.readAllBytes(file.toPath()));
				os.close();
			}
		}
		else {
			headervalue2.add("text/html");
			he.getResponseHeaders().put("Content-Type", headervalue2);
			String response = "<title>404 Not Found</title> 404 not found :( please try a different path.";
			OutputStream os = he.getResponseBody();
			he.sendResponseHeaders(404, file.length());
			os.write(response.getBytes());
			os.close();
		}
	}
}