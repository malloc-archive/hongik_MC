package malloc.hongikv1.sori;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.bukkit.Bukkit;

import privateinfo.Infos;

public class Anony {
	static String url = Infos.url;
	public static void sendDiscord(String message) {
		try {
            
            String jsonPayload = String.format("{\"content\": \"%s\"}", escapeJson(message));
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
            		.uri(URI.create(url))
            		.header("Content-Type", "application/json")
            		.POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
            		.build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if(response.statusCode() != 204 && response.statusCode() != 200) {
            	Bukkit.getConsoleSender().sendMessage("Failed to send webhook. CODE:" + response.statusCode() + " CONTENT:" + message);
            }
            		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static String escapeJson(String text) {
		return text.replace("\\", "\\\\")
				.replace("\"", "\\\"")
				.replace("\n", "\\n")
				.replace("\r", "");
	}
	
}
