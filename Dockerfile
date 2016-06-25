# Thin Java container from docker.io
FROM frolvlad/alpine-oraclejdk8

MAINTAINER Anand Dhingra <anand@ananddhingra.com>

WORKDIR /data

EXPOSE 8080

#------------------------------------------------------------------------------
# PRCHECKER OS environment variables
#------------------------------------------------------------------------------

# The auth token for your Slack account
ENV PR_SLACK_AUTH_TOKEN=

# The name of the Slack Channel to publish aged Pull Requests
ENV PR_SLACK_CHANNEL=

# The GitHub user
ENV PR_GITHUB_REPO=

# The GitHub auth token
ENV PR_GITHUB_AUTH_TOKEN=

# The GitHub repository to check for aged Pull Requests
ENV PR_GITHUB_USER=

# Age of Pull Request in days.  PR's older than this will be published to Slack
ENV PR_DAYS=

# Time in milliseconds to refresh the check
ENV PR_CHECK_FREQUENCY=
#------------------------------------------------------------------------------

# The PRCHECKER jar file, built from Maven, gets added to the container
ADD target/prchecker-1.0-SNAPSHOT.jar /data/prchecker.jar
ADD application.properties /data/application.properties

# The command to launch in the container. The following can be used for
# debugging by placing the above properties in a local props file
ENTRYPOINT ["java", "-jar", "/data/prchecker.jar", "--spring.config.location=/data/application.properties"]

# For production, no properties file passed.  Instead, edit the props above
#ENTRYPOINT ["java", "-jar", "/data/prchecker.jar"]

