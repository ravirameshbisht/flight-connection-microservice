package temesgen.girmay.microservice.connectionbuilder.configuration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class AMQPConfiguration {

    @Bean
    public TopicExchange masterDataTopicExchange(
            @Value("${amqp.exchange.master.data}") final String exchangeName) {
        return ExchangeBuilder.topicExchange(exchangeName).durable(true).build();
    }

    @Bean
    public Queue airportQueue(
            @Value("${amqp.queue.airport}") final String queueName) {
        return QueueBuilder.durable(queueName).build();
    }

    @Bean
    public Queue scheduleQueue(
            @Value("${amqp.queue.schedule}") final String queueName) {
        return QueueBuilder.durable(queueName).build();
    }


    @Bean
    public Binding airportsBinding(final Queue airportQueue,
                                          final TopicExchange masterDataTopicExchange) {
        return BindingBuilder.bind(airportQueue)
                .to(masterDataTopicExchange)
                .with("connection.flight.airports");
    }

    @Bean
    public Binding flightsBinding(final Queue scheduleQueue,
                                   final TopicExchange masterDataTopicExchange) {
        return BindingBuilder.bind(scheduleQueue)
                .to(masterDataTopicExchange)
                .with("connection.flight.schedules");
    }

    @Bean
    public MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();

        final MappingJackson2MessageConverter jsonConverter =
                new MappingJackson2MessageConverter();
        jsonConverter.getObjectMapper().registerModule(
                new ParameterNamesModule(JsonCreator.Mode.PROPERTIES));

        factory.setMessageConverter(jsonConverter);
        return factory;
    }

    @Bean
    public RabbitListenerConfigurer rabbitListenerConfigurer(
            final MessageHandlerMethodFactory messageHandlerMethodFactory) {
        return (c) -> c.setMessageHandlerMethodFactory(messageHandlerMethodFactory);
    }

}
