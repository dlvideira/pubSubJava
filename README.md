# Google PubSub in Java

This project shows in a simple way how to implement Google PubSub using Java.

## Installation

The project is using Java 16, but you can use at least Java 10 (because of the "var").

You will need a Google Cloud account and credentials, please refer to https://cloud.google.com to get those.

## Usage

```
1 - Create a topic using the "main" method in CreateTopic;

2 - Create a subscriber to this topic in CreateSubscriber. 
    You can create N subscribers to this topic;

3 - Run ReadFromTopic01;

4 - Run PublishToTopic.

5 - Done!
```

You can refer to https://cloud.google.com/pubsub/docs/overview to learn more about the relation of Publisher/Subscriber.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)