# Pull Request Checker
The Pull Request Checker is a Spring Boot Application which periodically checks a GitHub repository for aging pull requests, then sends notifications to a Slack Channel.

This application can run in Maven, Docker, or via Docker Compose.

## Technologies Used
[Spring Boot] (http://projects.spring.io/spring-boot/)

[Simple Slack API] (https://github.com/Ullink/simple-slack-api)

[Eclipse EGit Github Connector] (https://github.com/eclipse/egit-github)

[Docker] (https://www.docker.com/)

[Docker Compose] (https://docs.docker.com/compose/)

## How to Use (TL;DR)
```
cd worker; mvn clean package
vi docker-compose.yml
docker-compose up
```

**Build**

The `pom.xml` file included in the repository is Maven compliant, and has all dependencies defined.  A Maven environment is all that is required to build this project successfully.
```
mvn clean package
```
**Runtime Environment Settings**

Set the following environment variables at the OS level.  Alternatively, you can place these in an application.properties file.  See the [Spring Externalized Configuration] (http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files) documentation for details.
```
# The auth token for your Slack account
PR_SLACK_AUTH_TOKEN=

# The name of the Slack Channel to publish aged Pull Requests
PR_SLACK_CHANNEL=

# The GitHub user
PR_GITHUB_USER=

# The GitHub auth token
PR_GITHUB_AUTH_TOKEN=

# The GitHub repository to check for aged Pull Requests
PR_GITHUB_REPO=

# The age of the Pull Request, in days.  PR's older than this will be published to Slack
PR_DAYS=
```
**How to Run**

Run via Spring Boot
```
mvn spring-boot:run
```
## License
[Apache 2.0 License] (https://github.com/adhingra603/prchecker/blob/master/LICENSE)
