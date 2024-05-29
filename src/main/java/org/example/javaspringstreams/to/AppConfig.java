package org.example.javaspringstreams.to;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AppConfig {
    private final String sampleName = "to";
    private final String sourceName = sampleName + "-source";
    private final String sinkName = sampleName + "-sink";

    @Bean
    NewTopic InputTopic() {
        return TopicBuilder.name(sourceName)
            .partitions(3)
            .replicas(2)
            .build();
    }

    @Bean
    NewTopic OutputTopic() {
        return TopicBuilder.name(sinkName)
            .partitions(3)
            .replicas(2)
            .build();
    }

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        new To(streamsBuilder);
    }
}
