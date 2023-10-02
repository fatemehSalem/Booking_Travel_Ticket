package com.micro.temporaryreservationservice.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
@Table(name = "outbox")
/*@TypeDefs(
    TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
)*/
//Note: The type 'jsonb' is not supported in Hibernate 6 or later versions.
/*Thanks to the JSON mapping introduced in Hibernate 6, you only need to annotate your entity attribute with
 a @JdbcTypeCode annotation and set the type to SqlTypes.JSON. Hibernate then detects a JSON library on your
  classpath and uses it to serialize and deserialize the attribute’s value.*/

data class OutBox(
    @Id
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    var id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    var aggregateType: String,

    @Column(nullable = false)
    var aggregateId: Long,

    @Column(nullable = false)
    var eventType: String,

    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    var payload: String
){
    constructor() : this(UUID.randomUUID(), "", 0, "", "")
}


