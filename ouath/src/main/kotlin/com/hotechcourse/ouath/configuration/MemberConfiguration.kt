package com.hotechcourse.ouath.configuration

import com.hotechcourse.ouath.entity.Member
import com.hotechcourse.ouath.repository.MemberRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class MemberConfiguration {
    @Bean
    fun initMembers(memberRepository: MemberRepository) = ApplicationRunner {
        memberRepository.saveAll(listOf(
            Member("42seouler@gmail.com", "google", "Monika", listOf("ROLE_ADMIN", "ROLE_USER")),
            Member("jack@gmail.com", "google", "Jack", listOf("ROLE_USER")),
            Member("peter@gmail.com", "google", "Peter", listOf("ROLE_USER"))
        ))
    }
}