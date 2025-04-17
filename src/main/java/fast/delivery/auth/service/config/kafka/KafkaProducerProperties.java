package fast.delivery.auth.service.config.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Slf4j
@ConfigurationProperties(prefix = "kafka")
public class KafkaProducerProperties {

    private Map<String, ProducerConfig> producers = new HashMap<>();

    @Setter
    @Getter
    public static class ProducerConfig {
        private String keySerializer;
        private String valueSerializer;
        private String acks;
        private boolean enableIdempotence;
        private int retries;
        private int maxInFlightRequestsPerConnection;
        private int batchSize;
        private int lingerMs;
        private int bufferMemory;

    }
}
