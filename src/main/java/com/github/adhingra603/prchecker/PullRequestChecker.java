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
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by adhingra on 6/22/16.
 */
@Component
public class PullRequestChecker {

    // All of these should be retrieved from the OS ENV
    private static String PR_SLACK_AUTH_TOKEN     = "xoxp-52866333655-52866333719-52988455634-df522d8d97";
    private static final String PR_SLACK_CHANNEL  = "general";

    private static String PR_GUTHUB_REPO          = "hello-world";
    private static String PR_GITHUB_AUTH_TOKEN    = "7ac4f3478794d4557b5148d94845e520bf55935a";
    private static String PR_GITHUB_USER          = "adhingra603";

    final static long day_in_ms                   = 1000 * 60 * 60 * 24;
    final static int days = 3;
    private static Date PR_AGE                    = new Date(System.currentTimeMillis() - days * day_in_ms);


    @Scheduled(fixedRate = 5000)
    public static void checkPullRequests() throws Exception {

        // GitHub API token
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(PR_GITHUB_AUTH_TOKEN);

        RepositoryId rid = new RepositoryId(PR_GITHUB_USER, PR_GUTHUB_REPO);
        PullRequestService prservice = new PullRequestService(client);

        sendMessagesToChannel(getPullRequests(rid, prservice, PR_AGE));
//        List<String> strings = Arrays.asList("foo", "bar", "baz");
//        sendMessagesToChannel(strings);
    }

    /**
     *
     * Gets pull requests which are older than a certain amount of time.
     *
     * @param rid           Repository ID
     * @param prservice     The PullRequest Service
     * @param age
     * @return
     * @throws IOException
     */
    private static List<String> getPullRequests(RepositoryId rid, PullRequestService prservice, Date age) throws IOException {

        ArrayList<String> messages = new ArrayList<String>();

        for (PullRequest pr : prservice.getPullRequests(rid, "open")) {

            final String message = "Pull request \"{0}\" on {1} by \"{2}\" opened on \"{3}\"";

            if (pr.getCreatedAt().before(PR_AGE))
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
    public static void sendMessagesToChannel(List<String> messages) throws Exception {

        SlackSession session = SlackSessionFactory.createWebSocketSlackSession(PR_SLACK_AUTH_TOKEN);
        session.connect();

        SlackChannel c = session.findChannelByName(PR_SLACK_CHANNEL);

        for (String s: messages)
            session.sendMessage(c, s);

    }

}

