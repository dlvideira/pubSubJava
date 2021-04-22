package pubsub;

import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.cloud.pubsub.v1.Subscriber;
import com.google.pubsub.v1.ProjectSubscriptionName;
import com.google.pubsub.v1.PubsubMessage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadFromTopic02 {
    public static void main(String... args) throws Exception {

        String projectId = "study-285802";
        String subscriptionId = "study-order-sub-02";

        subscribe(projectId, subscriptionId);
    }

    private static void subscribe(String projectId, String subscriptionId) {
        var subscriptionName = ProjectSubscriptionName.of(projectId, subscriptionId);
        AtomicInteger n = new AtomicInteger();
        MessageReceiver receiver =
                (PubsubMessage message, AckReplyConsumer consumer) -> {
                    System.out.println("Id: " + message.getMessageId());
                    System.out.println("Data: " + message.getData().toStringUtf8());
                    consumer.ack();
                    n.getAndIncrement();
                    System.out.println(n.get() + " no 02");
                };
        Subscriber subscriber = null;
        try {
            subscriber = Subscriber.newBuilder(subscriptionName, receiver).build();

            subscriber.startAsync().awaitRunning();
            System.out.printf("Escutando mensagens %s:\n", subscriptionName.toString());

            subscriber.awaitTerminated(30, TimeUnit.HOURS);
        } catch (Exception e) {
            subscriber.stopAsync();
        }
    }
}
