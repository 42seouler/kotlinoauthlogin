package com.hotechcourse.ouath.entity

import javax.persistence.*

@Entity
data class Member(
    @Column(unique = true) val username: String,
    val registrationId: String,
    val name: String,
    @ElementCollection val authorities: Collection<String>,
    @Id @GeneratedValue var id: Long? = null
)