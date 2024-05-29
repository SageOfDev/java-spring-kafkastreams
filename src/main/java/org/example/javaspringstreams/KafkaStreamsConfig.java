package org.example.javaspringstreams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

@Configuration
@EnableKafkaStreams
public class KafkaStreamsConfig {

//    @Value(value = "${spring.application.name}")
//    private String applicationId;
//    @Value(value = "${spring.kafka.bootstrap-servers}")
//    private String bootstrapServerAddresses;
//    @Value(value = "${spring.kafka.application-server}")
//    private String applicationServerAddress;
//    @Value(value = "${spring.kafka.streams.state.dir}")
//    private String stateStoreLocation;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    KafkaStreamsConfiguration kStreamsConfig(KafkaProperties kafkaProperties) {
//        HashMap<String, Object> props  =new HashMap<>();
        Map<String, Object> props = kafkaProperties.buildStreamsProperties(null);
        props.putAll(kafkaProperties.buildConsumerProperties(null));
        props.putAll(kafkaProperties.buildProducerProperties(null));
//        props.put(APPLICATION_ID_CONFIG, applicationId);
//        props.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapServerAddresses);
//        props.put(APPLICATION_SERVER_CONFIG, applicationServerAddress);
        props.put(DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.put(STATE_DIR_CONFIG, stateStoreLocation);



        return new KafkaStreamsConfiguration(props);
    }
}
