package githubuseractivity;

import java.util.ArrayList;

public class GithubActivity {

    public static void main(String[] args) {
        // check if the username is provided.
        if (args.length == 0) {
            System.out.println("Error: Github username is required");
            System.out.println("Usage: Java GithubActivity <username>");
            System.out.println("Example: Java GihubActivity jqpython");
            return;
        }

        String username = args[0];

        try {
            // Fetch activity from GitHub API
            GitHubAPI api = new GitHubAPI();
            System.out.println(
                "Fetching activity for user: " + username + "...\n"
            );

            ArrayList<GitHubEvent> events = api.fetchUserActivity(username);

            // Display results
            if (events.isEmpty()) {
                System.out.println(
                    "No recent activity found for user: " + username
                );
            } else {
                System.out.println("Recent activity for " + username + ":");
                System.out.println();

                for (GitHubEvent event : events) {
                    System.out.println(event);
                }

                System.out.println("\nTotal events: " + events.size());
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());

            // Provide helpful hints based on error
            if (e.getMessage().contains("User not found")) {
                System.err.println("Make sure the username is correct.");
            } else if (e.getMessage().contains("Rate limit")) {
                System.err.println(
                    "GitHub limits requests to 60 per hour. Please wait a bit."
                );
            }
        }
    }
}
