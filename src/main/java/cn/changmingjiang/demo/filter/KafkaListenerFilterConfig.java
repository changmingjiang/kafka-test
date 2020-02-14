package cn.changmingjiang.demo.filter;

import cn.changmingjiang.demo.domain.KafkaMessage;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 配置消息过滤器
 */
@Configuration
public class KafkaListenerFilterConfig {
	@Resource
	private ConsumerFactory<Object, Object> kafkaConsumerFactory;

	private static final List<String> PROD_CODE_PLUS = Arrays.asList( "PLUS", "PLUS0", "PLUS1" );

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerFilterContainerFactory( ConcurrentKafkaListenerContainerFactoryConfigurer configurer ) {
		ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		configurer.configure( factory, kafkaConsumerFactory );
		// 是否自动丢弃
		factory.setAckDiscarded( true );
		factory.setRecordFilterStrategy( consumerRecord -> {
			KafkaMessage kafkaMessage = ( KafkaMessage ) consumerRecord.value();
			// 为true的话，消息将会被丢弃
			return !PROD_CODE_PLUS.contains( kafkaMessage.getProdCode() );
		} );
		return factory;
	}
}
