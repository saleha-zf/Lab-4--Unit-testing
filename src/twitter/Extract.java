package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extract {

    /**
     * Get the time period spanned by tweets.

     */
	//IMPLEMENTATION OF getTimespan
    public static Timespan getTimespan(List<Tweet> tweets) {
     
        // Initialize start and end with the timestamp of the first tweet
        Instant start = tweets.get(0).getTimestamp();
        Instant end = start;

        // Loop through tweets to find the earliest and latest timestamps
        for (Tweet tweet : tweets) {
            Instant timestamp = tweet.getTimestamp();
            if (timestamp.isBefore(start)) {
                start = timestamp;
            }
            if (timestamp.isAfter(end)) {
                end = timestamp;
            }
        }

        return new Timespan(start, end);
    }

//IMPLEMENTATION OF getMentionedUsers
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> mentionedUsers = new HashSet<>();
        Pattern mentionPattern = Pattern.compile("(?<!\\w)@(\\w+)", Pattern.CASE_INSENSITIVE);

        // Loop through all tweets
        for (Tweet tweet : tweets) {
            String text = tweet.getText();
            Matcher matcher = mentionPattern.matcher(text);

            // Find all valid mentions in the tweet
            while (matcher.find()) {
                String username = matcher.group(1).toLowerCase(); // Case-insensitive
                mentionedUsers.add(username);
            }
        }

        return mentionedUsers;
    }

}
