# Thin Java container from docker.io
FROM frolvlad/alpine-oraclejdk8

MAINTAINER Anand Dhingra <anand@ananddhingra.com>

WORKDIR /data

# The PRCHECKER jar file, built from Maven, gets added to the container
COPY target/prchecker-1.0-SNAPSHOT.jar /data/prchecker.jar

#-----------------------------------
# PRCHECKER OS environment variables
#-----------------------------------
# The auth token for your Slack account
#ENV PR_SLACK_AUTH_TOKEN=

# The name of the Slack Channel to publish aged Pull Requests
#ENV PR_SLACK_CHANNEL=

# The GitHub user
#ENV PR_GITHUB_REPO=

# The GitHub auth token
#ENV PR_GITHUB_AUTH_TOKEN=

# The GitHub repository to check for aged Pull Requests
#ENV PR_GITHUB_USER=

# Age of Pull Request in days.  PR's older than this will be published to Slack
#ENV PR_DAYS=

# Time in milliseconds to refresh the check
#ENV PR_CHECK_FREQUENCY=
#------------------------------------------------------------------------------
CMD ["java", "-jar", "/data/prchecker.jar"]
