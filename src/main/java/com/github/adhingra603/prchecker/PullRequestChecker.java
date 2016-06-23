package com.github.adhingra603.prchecker;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;
import org.eclipse.egit.github.core.PullRequest;
import org.eclipse.egit.github.core.RepositoryId;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.PullRequestService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by adhingra on 6/22/16.
 */
@Component
public class PullRequestChecker {

    @Scheduled(fixedRate = 5000)
    public static void checkPullRequests(PRProperties prProperties) throws Exception {

        // GitHub API token
        GitHubClient client = new GitHubClient();
//        client.setOAuth2Token(PR_GITHUB_AUTH_TOKEN);
        client.setOAuth2Token(prProperties.getGithubAuthToken());

        // GitHub Repository
//        RepositoryId rid = new RepositoryId(PR_GITHUB_USER, PR_GITHUB_REPO);
        RepositoryId rid = new RepositoryId(prProperties.getGithubUser(), prProperties.getGithubRepo());
        PullRequestService prservice = new PullRequestService(client);

        // Construct a Date object which represents the threshold date of the Pull Request
        long day_in_ms = 1000 * 60 * 60 * 24;
//        Date prAge = new Date(System.currentTimeMillis() - PR_DAYS * day_in_ms);
        Date prAge = new Date(System.currentTimeMillis() - prProperties.getPrAge() * day_in_ms);

        List messages = getPullRequests(rid, prservice, prAge);
        sendMessagesToChannel(messages, prProperties.getSlackAuthToken(), prProperties.getSlackChannel());

    }

    /**
     *
     * Gets pull requests which are older than a certain amount of time.
     *
     * @param rid           Repository ID
     * @param prservice     The PullRequest Service
     * @param age           Pull requests older than this will be returned
     * @return messages     A list of pull requests which are older than the specified age
     * @throws IOException
     */
    private static List<String> getPullRequests(RepositoryId rid, PullRequestService prservice, Date age) throws IOException {

        ArrayList<String> messages = new ArrayList<>();

        for (PullRequest pr : prservice.getPullRequests(rid, "open")) {

            final String message = "Pull request \"{0}\" on {1} by \"{2}\" opened on \"{3}\"";

            if (pr.getCreatedAt().before(age))
                messages.add(MessageFormat.format(message,
                        pr.getTitle(),
                        pr.getHead().getRef(),
                        pr.getUser().getLogin(),
                        pr.getCreatedAt()
                ));
        }
        return messages;
    }


    /**
     *
     * Sends a message to a Slack Channel
     *
     * @param messages      List of messages to send to the channel.
     * @throws Exception    In case the SlackSession can not be opened.  TODO: Handle error and report to user
     *
     */
    private static void sendMessagesToChannel(List<String> messages, String token, String channel) throws Exception {

        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(token);
        session.connect();

        SlackChannel c = session.findChannelByName(channel);

        for (String s: messages)
            session.sendMessage(c, s);

    }

}

