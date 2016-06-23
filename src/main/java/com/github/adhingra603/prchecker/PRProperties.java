package com.github.adhingra603.prchecker;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.expression.EnvironmentAccessor;
import org.springframework.core.env.Environment;

import javax.inject.Inject;

/**
 * Bean containing the environment properties for Git and Slack
 *
 * Created by adhingra on 6/23/16.
 */

@Configuration
public class PRProperties {

    private String slackAuthToken;
    private String slackChannel;

    private String githubUser;
    private String githubAuthToken;
    private String githubRepo;

    private int prAge;

    public int getPrAge() {
        return prAge;
    }

    public void setPrAge(int prAge) {
        this.prAge = Integer.getInteger(env.getProperty("prAge"));
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    @Inject Environment env;

    public PRProperties() {

    }

    public String getSlackAuthToken() {
        return slackAuthToken;
    }

    public void setSlackAuthToken(String slackAuthToken) {
        this.slackAuthToken = env.getProperty("slackAuthToken");
    }

    public String getSlackChannel() {
        return slackChannel;
    }

    public void setSlackChannel(String slackChannel) {
        this.slackChannel = env.getProperty("slackChannel");
    }

    public String getGithubUser() {
        return githubUser;
    }

    public void setGithubUser(String githubUser) {
        this.githubUser = env.getProperty("githubUser");
    }

    public String getGithubAuthToken() {
        return githubAuthToken;
    }

    public void setGithubAuthToken(String githubAuthToken) {
        this.githubAuthToken = env.getProperty("githubAuthToken");
    }

    public String getGithubRepo() {
        return githubRepo;
    }

    public void setGithubRepo(String githubRepo) {
        this.githubRepo = env.getProperty("githubRepo");
    }
}
