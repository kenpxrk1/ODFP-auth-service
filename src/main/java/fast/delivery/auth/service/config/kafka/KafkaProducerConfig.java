package fast.delivery.auth.service.config.kafka;

import fast.delivery.auth.service.dto.response.RegisteredUserEventDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(KafkaProducerProperties.class)
public class KafkaProducerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    private final KafkaProducerProperties producerProperties;

    @Bean
    public ProducerFactory<String, RegisteredUserEventDto> registrationProducerFactory() {
        KafkaProducerProperties.ProducerConfig config =
                producerProperties.getProducers().get("registration");

        JsonSerializer<RegisteredUserEventDto> jsonSerializer = new JsonSerializer<>();
        jsonSerializer.setAddTypeInfo(false);

        Map<String, Object> configProps = buildConfig(config);
        return new DefaultKafkaProducerFactory<>(
                configProps,
                new StringSerializer(),
                jsonSerializer
        );
    }

    @Bean
    public KafkaTemplate<String, RegisteredUserEventDto> registrationKafkaTemplate() {
        return new KafkaTemplate<>(registrationProducerFactory());
    }

    private Map<String, Object> buildConfig(KafkaProducerProperties.ProducerConfig config) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, config.getKeySerializer());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, config.getAcks());
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, config.isEnableIdempotence());
        configProps.put(ProducerConfig.RETRIES_CONFIG, config.getRetries());
        configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION,
                config.getMaxInFlightRequestsPerConnection());
        configProps.put(ProducerConfig.BATCH_SIZE_CONFIG, config.getBatchSize());
        configProps.put(ProducerConfig.LINGER_MS_CONFIG, config.getLingerMs());
        configProps.put(ProducerConfig.BUFFER_MEMORY_CONFIG, config.getBufferMemory());
        return configProps;
    }
}
