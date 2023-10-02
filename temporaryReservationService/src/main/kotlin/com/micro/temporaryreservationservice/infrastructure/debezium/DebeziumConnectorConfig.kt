package com.micro.temporaryreservationservice.infrastructure.debezium

import io.debezium.config.Configuration
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import java.io.IOException

@org.springframework.context.annotation.Configuration
class DebeziumConfiguration {

    @Value("\${spring.datasource.url}")
    private lateinit var dbUrl: String

    @Value("\${spring.datasource.username}")
    private lateinit var dbUsername: String

    @Value("\${spring.datasource.password}")
    private lateinit var dbPassword: String

    @Bean
    @Throws(IOException::class)
    fun customerConnector(): Configuration {
        return Configuration.create()
            .with("name", "customer_outbox_connector")
            .with("tasks.max","1")
            .with("database.hostname", dbUrl.substringAfter("//").substringBefore(":"))
            .with("database.port", dbUrl.substringAfter(":").substringBefore("/"))
            .with("database.user", dbUsername)
            .with("database.password", dbPassword)
            .with("database.dbname", "preservation-db")
            .with("database.server.name", "postgres")
            .with("table.whitelist", "preservation-db.outbox")
            .with("transforms", "outbox")
            .with("transforms.outbox.type", "io.debezium.transforms.outbox.EventRouter")
            .with("transforms.outbox.table.field.event.id", "id")
            .with("transforms.outbox.table.field.event.key", "aggregateId")
            .with("transforms.outbox.table.field.event.type", "eventType")
            .with("transforms.outbox.table.field.event.payload.id", "aggregateId")
            .with("key.converter", "org.apache.kafka.connect.storage.StringConverter")
            .with("key.converter.schemas.enable", "false")
            .with("value.converter", "org.apache.kafka.connect.json.JsonConverter")
            .with("value.converter.schemas.enable", "false")
            .with("include.schema.changes", "false")
            .with("topic", "reservation-request")
            .build()
    }

}