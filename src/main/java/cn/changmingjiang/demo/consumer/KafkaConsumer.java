package cn.changmingjiang.demo.consumer;

import cn.changmingjiang.demo.domain.KafkaMessage;
import cn.changmingjiang.demo.util.JsonHelper;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class KafkaConsumer {

    @KafkaListener(id = "${spring.kafka.consumer.group-id}", topics = {"${spring.kafka.template.default-topic}"},
            containerFactory = "kafkaListenerFilterContainerFactory")
    public void consumerMessage( ConsumerRecord<String, KafkaMessage> consumerRecord,
								 Acknowledgment acknowledgment) {
        KafkaMessage message = consumerRecord.value();

        log.info("kafka消息接收 - 接收到消息 - offset:{};message:{}", consumerRecord.offset(),
                JsonHelper.object2Json(message));
        try {

            // 处理kafkaMessage

            // 手动确认消息
            acknowledgment.acknowledge();
        } catch (Exception e) {
            log.error("kafka消息接收 - 发生异常 - offset:{};message:{};异常信息:{}", consumerRecord.offset(),
                    JsonHelper.object2Json(message), e.getMessage(), e);
        }
    }
}
