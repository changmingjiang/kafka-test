package cn.changmingjiang.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

@SpringBootApplication
public class KafkaTestApplication implements ApplicationListener<ContextRefreshedEvent> {
	@Resource
	private KafkaTemplate<Object, Object> kafkaTemplate;

	public static void main( String[] args ) {
		SpringApplication.run( KafkaTestApplication.class, args );
	}

	@Override
	public void onApplicationEvent( ContextRefreshedEvent event ) {
		String playLoad1 = "{\"clientId\":\"xxxx\",\"clientType\":0,\"portalId\":\"vvvv\",\"prodCode\":\"level2\"," +
				"\"amount\":1,\"startTime\":1239297234923234,\"endTime\":1239397234923234,\"mobile\":\"13888888\"}";
		kafkaTemplate.send( "test", "sale", playLoad1 );
		String playLoad2 = "{\"clientId\":\"xxxx\",\"clientType\":0,\"portalId\":\"vvvv\",\"prodCode\":\"PLUS\"," +
				"\"amount\":1,\"startTime\":1239297234923234,\"endTime\":1239397234923234,\"mobile\":\"13888888\"}";
		kafkaTemplate.send( "test", "sale", playLoad2 );
		String playLoad3 = "{\"clientId\":\"xxxx\",\"clientType\":0,\"portalId\":\"vvvv\",\"prodCode\":\"PLUS0\"," +
				"\"amount\":1,\"startTime\":1239297234923234,\"endTime\":1239397234923234,\"mobile\":\"13888888\"}";
		kafkaTemplate.send( "test", "sale", playLoad3 );
		String playLoad4 = "{\"clientId\":\"xxxx\",\"clientType\":0,\"portalId\":\"vvvv\",\"prodCode\":\"PLUS1\"," +
				"\"amount\":1,\"startTime\":1239297234923234,\"endTime\":1239397234923234,\"mobile\":\"13888888\"}";
		kafkaTemplate.send( "test", "sale", playLoad4 );
		String playLoad5 = "{\"clientId\":\"xxxx\",\"clientType\":0,\"portalId\":\"vvvv\",\"prodCode\":\"PLUSxx\"," +
				"\"amount\":1,\"startTime\":1239297234923234,\"endTime\":1239397234923234,\"mobile\":\"13888888\"}";
		kafkaTemplate.send( "test", "sale", playLoad5 );
	}
}
