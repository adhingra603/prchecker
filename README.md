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
mvn spring-boot:run
```

## License
[Apache 2.0 License] (https://github.com/adhingra603/prchecker/blob/master/LICENSE)
