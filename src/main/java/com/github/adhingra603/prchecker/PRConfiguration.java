/*
 * Copyright 2016 Anand Dhingra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.adhingra603.prchecker;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Bean containing the environment properties for Git and Slack
 *
 * Created by adhingra on 6/23/16.
 */

@Component
@ConfigurationProperties(prefix="pr")
public class PRConfiguration {


    private String slackAuthToken;
    private String slackChannel;
    private String githubUser;
    private String githubAuthToken;
    private String githubRepo;
    private int prAge;
    private int checkFrequency;

    public int getCheckFrequency() {
        return checkFrequency;
    }

    public void setCheckFrequency(int checkFrequency) {
        this.checkFrequency = checkFrequency;
    }

    public int getPrAge() {
        return prAge;
    }

    public void setPrAge(int prAge) {
        this.prAge = prAge;
    }

    public String getSlackAuthToken() {
        return slackAuthToken;
    }

    public void setSlackAuthToken(String slackAuthToken) {
        this.slackAuthToken = slackAuthToken;
    }

    public String getSlackChannel() {
        return slackChannel;
    }

    public void setSlackChannel(String slackChannel) {
        this.slackChannel = slackChannel;
    }

    public String getGithubUser() {
        return githubUser;
    }

    public void setGithubUser(String githubUser) {
        this.githubUser = githubUser;
    }

    public String getGithubAuthToken() {
        return githubAuthToken;
    }

    public void setGithubAuthToken(String githubAuthToken) {
        this.githubAuthToken = githubAuthToken;
    }

    public String getGithubRepo() {
        return githubRepo;
    }

    public void setGithubRepo(String githubRepo) {
        this.githubRepo = githubRepo;
    }
}
