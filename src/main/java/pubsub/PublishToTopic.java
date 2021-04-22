package pubsub;

import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.Publisher;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.PubsubMessage;
import com.google.pubsub.v1.TopicName;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class PublishToTopic {

    public static void main(String[] args) throws Exception {
        String projectId = "study-285802";
        String topicId = "study-order";

        publishMessage(projectId, topicId);

    }

    private static void publishMessage(String projectId, String topicId) throws InterruptedException, IOException, ExecutionException {
        TopicName topic = TopicName.of(projectId, topicId);

        Publisher publisher = null;

        try {
            publisher = Publisher.newBuilder(topic).build();

            String messageToPublish = """
                    orderId : order001,
                    createdDate : $date
                    orderAmount : 123.43,
                    sku : sales111,
                    shopId : 2059
                    """.replace("$date", LocalDateTime.now().toString());
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(ByteString.copyFromUtf8(messageToPublish)).build();
            ApiFuture<String> messageIdFuture = publisher.publish(pubsubMessage);
            String messageId = messageIdFuture.get();
            System.out.println("Mensagem publicada com o ID: " + messageId);
        } finally {
            if (publisher != null) {
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }
}
