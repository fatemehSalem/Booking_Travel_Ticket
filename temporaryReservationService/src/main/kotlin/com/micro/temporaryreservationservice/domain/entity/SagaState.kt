package com.micro.temporaryreservationservice.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "sagaState")
data class SagaState(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

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
    constructor() : this(0, "", "", "", "", "", 0)
}
