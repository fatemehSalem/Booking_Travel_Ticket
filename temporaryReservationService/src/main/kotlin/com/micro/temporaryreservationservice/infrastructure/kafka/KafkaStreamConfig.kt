package com.micro.temporaryreservationservice.infrastructure.kafka

import com.micro.commonservice.Order
import com.micro.temporaryreservationservice.domain.model.GenericJsonSerializer
import com.micro.temporaryreservationservice.infrastructure.service.ManageTemporaryReservationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.scheduling.annotation.EnableAsync
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.*
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier
import org.apache.kafka.streams.state.Stores
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.StreamsBuilderFactoryBean
import org.springframework.kafka.support.serializer.JsonSerde
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.time.Duration

@EnableKafkaStreams
@EnableAsync
@Configuration
class KafkaStreamConfig(
    private val manageTemporaryReservationService: ManageTemporaryReservationService,
) {
    @Value("\${spring.kafka.consumer.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    private val LOG: Logger = LoggerFactory.getLogger(KafkaStreamConfig::class.java)

    @Bean
    fun streamsBuilderFactoryBean(): StreamsBuilderFactoryBean {
        val streamsConfig = StreamsBuilderFactoryBean()
        streamsConfig.streamsConfiguration?.set("bootstrap.servers", bootstrapServers)
        streamsConfig.streamsConfiguration?.set("default.key.serde", Serdes.String().javaClass.name)
        streamsConfig.streamsConfiguration?.set("default.value.serde", GenericJsonSerializer::class.java.name)
        return streamsConfig
    }

    @Bean
    fun stream(builder: StreamsBuilder): KStream<Long, Order> {
        val orderSerde = JsonSerde(Order::class.java)
        val stream = builder.stream("payment-response-topic", Consumed.with(Serdes.Long(), orderSerde))

        stream.join(
            builder.stream("reservation-response-topic"),
            { paymentOrder, stockOrder -> manageTemporaryReservationService.confirm(paymentOrder, stockOrder) },
            JoinWindows.of(Duration.ofSeconds(10)),
            StreamJoined.with(Serdes.Long(), orderSerde, orderSerde)
        ).peek { _, order -> LOG.info("Output: {}", order) }
            .to("temp-reservation-topic")

        return stream
    }

    @Bean
    fun table(builder: StreamsBuilder): KTable<Long, Order> {
        val store: KeyValueBytesStoreSupplier = Stores.persistentKeyValueStore("temp-reservation-topic")
        val orderSerde = JsonSerde(Order::class.java)
        val stream = builder.stream("temp-reservation-topic", Consumed.with(Serdes.Long(), orderSerde))
        return stream.toTable(
            Materialized.`as`<Long, Order>(store)
                .withKeySerde(Serdes.Long())
                .withValueSerde(orderSerde)
        )
    }

    @Bean
    fun taskExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 5
        executor.maxPoolSize = 5
      //  executor.threadNamePrefix = "kafkaSender-" //check in Kotlin!!
        executor.initialize()
        return executor
    }

}