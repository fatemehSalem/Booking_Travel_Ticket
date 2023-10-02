package com.micro.temporaryreservationservice.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.UUID

@Entity
@Table(name = "sagaState")
data class SagaState(
    @Id
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    var id: UUID = UUID.randomUUID(),

    @Column
    var currentStep: String,

    @Column
    @JdbcTypeCode(SqlTypes.JSON)
    var payload: String,

    @Column
    var status: String,

    @Column
    @JdbcTypeCode(SqlTypes.JSON)
    var stepState: String,

    @Column
    var type: String,

    @Column
    var version: Int,
){
    constructor() : this(UUID.randomUUID(), "", "", "", "", "", 0)
}
