package fast.delivery.auth.service.event;

import fast.delivery.auth.service.dto.response.RegisteredUserEventDto;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaUserProducer {
    @Value("${topic.user-registration}")
    private String userRegistrationTopic;
    private final KafkaTemplate<String, RegisteredUserEventDto> kafkaTemplate;

    public void sendUserCreated(RegisteredUserEventDto registeredUserEventDto) {
        CompletableFuture<SendResult<String, RegisteredUserEventDto>> future = kafkaTemplate.send(userRegistrationTopic,
                registeredUserEventDto);
        future.whenCompleteAsync((result, exception) -> {
            if (exception == null) {
                log.info("Message with userId={} sent successfully: {}", registeredUserEventDto.getExternalId(),
                        result.getRecordMetadata());
            } else {
                log.error("Failed to send message: {}", exception.getMessage());
            }
        });
    }
}
