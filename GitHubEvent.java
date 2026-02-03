package githubuseractivity;

public class GitHubEvent {

    private String type;
    private String repoName;

    public GitHubEvent(String type, String repoName) {
        this.type = type;
        this.repoName = repoName;
    }

    public String getType() {
        return type;
    }

    public String getRepoName() {
        return repoName;
    }

    @Override
    public String toString() {
        // Basic formatting based on type
        if (type.equals("PushEvent")) {
            return "- Pushed commits to " + repoName;
        } else if (type.equals("WatchEvent")) {
            return "- Starred " + repoName;
        } else if (type.equals("IssuesEvent")) {
            return "- Created an issue in " + repoName;
        } else if (type.equals("ForkEvent")) {
            return "- Forked " + repoName;
        } else if (type.equals("CreateEvent")) {
            return "- Created a branch in " + repoName;
        } else if (type.equals("PullRequestEvent")) {
            return "- Opened a pull request in " + repoName;
        } else {
            // For any other event type, just display it as-is
            return "- " + type + " in" + repoName;
        }
    }
}
