package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.List;
import java.util.Properties;

public class SimpleKafkaConsumer {

    private KafkaConsumer<String, String> consumer;
    private Properties props;
    private List<String> topics;


    public void init() {
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(topics);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(10000);
                    try {
                        for (ConsumerRecord<String, String> record : records) {
                            System.out.println(record);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public KafkaConsumer<String, String> getConsumer() {
        return consumer;
    }

    public SimpleKafkaConsumer setConsumer(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
        return this;
    }

    public Properties getProps() {
        return props;
    }

    public SimpleKafkaConsumer setProps(Properties props) {
        this.props = props;
        return this;
    }

    public List<String> getTopics() {
        return topics;
    }

    public SimpleKafkaConsumer setTopics(List<String> topics) {
        this.topics = topics;
        return this;
    }
}
