package pubsub;

import com.google.cloud.pubsub.v1.SubscriptionAdminClient;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PushConfig;
import com.google.pubsub.v1.TopicName;

public class CreateSubscriber {
    public static void main(String[] args) {
        String projectId = "study-285802";
        String topicId = "study-order";
        String subscriptionId = "study-order-sub-01";

        createSubscriber(projectId, topicId, subscriptionId);
    }

    private static void createSubscriber(String projectId, String topicId, String subscriptionId) {
        var topic = TopicName.of(projectId, topicId);
        var subscription = ProjectSubscriptionName.of(projectId, subscriptionId);

        try {
            SubscriptionAdminClient.create().createSubscription(subscription, topic, PushConfig.getDefaultInstance(), 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
