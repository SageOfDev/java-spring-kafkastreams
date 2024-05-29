package org.example.javaspringstreams.to;

import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class To {
    private final String sampleName = "to";
    private final String sourceName = sampleName + "-source";
    private final String sinkName = sampleName + "-sink";

    public To(@Autowired StreamsBuilder streamsBuilder) {
        streamsBuilder.stream(sourceName)
            .to(sinkName);
    }
}
