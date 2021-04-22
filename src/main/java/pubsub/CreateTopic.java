package pubsub;

import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.pubsub.v1.TopicName;

public class CreateTopic {

    public static void main(String[] args) {
        String projectId = "study-285802";
        String topicId = "study-order";

        createTopic(projectId, topicId);
    }

    private static void createTopic(String projectId, String topicId) {
        var topic = TopicName.of(projectId, topicId);
        try {
            TopicAdminClient.create().createTopic(topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
