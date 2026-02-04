source: https://roadmap.sh/projects/github-user-activity

# GitHub Activity Tracker

A command-line tool to fetch and display a GitHub user's recent activity.

## Features

- Fetches recent activity using GitHub API
- Displays formatted activity (commits, stars, forks, issues, PRs)
- Error handling for invalid users and rate limiting
- No external dependencies - pure Java

## Usage
```bash
javac GitHubEvent.java GitHubAPI.java GitHubActivity.java
java GitHubActivity <username>
```

## Example
```bash
$ java GitHubActivity torvalds

Fetching activity for user: torvalds...

Recent activity for torvalds:
- Pushed commits to torvalds/linux
- Starred some-user/cool-project
- Forked another-repo/awesome-tool

Total events: 30
```

## Technologies

- Java (HttpURLConnection for HTTP requests)
- GitHub REST API
- Manual JSON parsing

## Project Requirements

- Java 8 or higher
- Internet connection
- No external libraries required

## Learning Outcomes

Built as part of my Java learning journey. Concepts practiced:
- HTTP GET requests
- API integration
- JSON parsing
- Command-line interfaces
- Error handling

## API Rate Limiting

GitHub allows 60 unauthenticated requests per hour. If you hit the limit, wait an hour or authenticate your requests.

## Future Enhancements

- [ ] Filter by event type
- [ ] Show detailed commit messages
- [ ] Cache results
- [ ] Add color-coded output
- [ ] Authentication for higher rate limits

---

**Roadmap Project:** https://roadmap.sh/projects/github-user-activity
