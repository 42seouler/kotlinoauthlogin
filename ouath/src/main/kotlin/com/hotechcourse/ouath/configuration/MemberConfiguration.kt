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
    fun initMember(memberRepository: MemberRepository, passwordEncoder: PasswordEncoder) = ApplicationRunner {
        memberRepository.saveAll(listOf(
            Member("nakim", passwordEncoder.encode("123456"), "nakim", listOf("ROLE_ADMIN", "ROLE_USER")),
            Member("jack", passwordEncoder.encode("123456"), "Jack", listOf("ROLE_USER")),
            Member("peter", "123456", "Peter", listOf("ROLE_USER"))
        ))
    }
}