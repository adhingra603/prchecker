# Pull Request Checker
The Pull Request Checker is a Spring Boot Application which periodically checks a GitHub repository for aging pull requests, then sends notifications to a Slack Channel.

## Technologies Used
[Spring Boot] (http://projects.spring.io/spring-boot/)

[Simple Slack API] (https://github.com/Ullink/simple-slack-api)

[Eclipse EGit Github Connector] (https://github.com/eclipse/egit-github)

## How to Use
Build from source
```
mvn clean package
```
Set the following environment variables at the OS level.  Alternatively, you can place these in an application.properties file.  See the [Spring] (http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files) for details
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
Run via Spring Boot
```
mvn spring-boot:run
```
## License
[Apache 2.0 License] (https://github.com/adhingra603/prchecker/blob/master/LICENSE)
