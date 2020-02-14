package cn.changmingjiang.demo.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * kafka消息类
 *
 * @author changming.jiang
 */
@Getter
@Setter
public class KafkaMessage {
    private String clientId;
    private Integer clientType;
    private String portalId;
    private Integer amount;
    private Long startTime;
    private Long endTime;
    private String prodCode;
}
