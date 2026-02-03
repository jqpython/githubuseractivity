package githubuseractivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class GitHubAPI {

    private static final String API_BASE_URL = "https://api.github.com";

    // Fetch user activity from Github API

    public ArrayList<GitHubEvent> fetchUserActivity(String username)
        throws Exception {
        String apiUrl = API_BASE_URL + "/users/" + username + "/events";

        // Make HTTP GET request
        String jsonResponse = makeHttpRequest(apiUrl);

        // Parse JSON response into GitHubEvent objects
        return parseEvents(jsonResponse);
    }

    // Make HTTP GET request
    private String makeHttpRequest(String urlString) throws Exception {
        URI uri = new URI(urlString);
        URL url = uri.toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set request method to GET
        connection.setRequestMethod("GET");

        // GitHub likes when you set User-Agent [HTTP header]
        connection.setRequestProperty("User-Agent", "GitHub-Activity-CLI");

        // Chec response code
        int responseCode = connection.getResponseCode();

        if (responseCode == 401) {
            throw new Exception("User not found");
        } else if (responseCode == 404) {
            throw new Exception("Rate limit exceeded");
        } else if (responseCode != 200) {
            throw new Exception("Request failed with code: " + responseCode);
        }

        // Read response
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(connection.getInputStream())
        );

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();

        return response.toString();
    }

    // Parse JSON response into list of events
    private ArrayList<GitHubEvent> parseEvents(String json) {
        ArrayList<GitHubEvent> events = new ArrayList<>();

        // Simple approach: find each event by looking for patterns
        int startIndex = 0;

        while (true) {
            // Find next event type
            int typeIndex = json.indexOf("\"type\":\"", startIndex);
            if (typeIndex == -1) break; // No more events

            // Extract type
            int typeStart = typeIndex + 8; // Skip past "type":"
            int typeEnd = json.indexOf("\"", typeStart);
            String type = json.substring(typeStart, typeEnd);

            // Find repo name (always comes after type in GitHub's JSON)
            int repoIndex = json.indexOf("\"name\":\"", typeEnd);
            if (repoIndex == -1) break;

            int repoStart = repoIndex + 8;
            int repoEnd = json.indexOf("\"", repoStart);
            String repoName = json.substring(repoStart, repoEnd);

            // Create event and add to list
            events.add(new GitHubEvent(type, repoName));

            // Move forward to find next event
            startIndex = repoEnd;
        }

        return events;
    }
}
