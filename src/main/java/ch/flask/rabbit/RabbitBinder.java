package ch.flask.rabbit;

import javax.enterprise.context.Dependent;

import net.reini.rabbitmq.cdi.EventBinder;
import net.reini.rabbitmq.cdi.JsonEncoder;

@Dependent
public class RabbitBinder extends EventBinder {
  @Override
  protected void bindEvents() {
    bind(EventOne.class)
      .toExchange("test.exchange.one");
    bind(EventOne.class)
      .toExchange("test.exchange.two")
      .withEncoder(new JsonEncoder<>())
      .withRoutingKey("test.key");

    bind(EventTwo.class)
      .toQueue("test.queue")
      .autoAck();
  }
}