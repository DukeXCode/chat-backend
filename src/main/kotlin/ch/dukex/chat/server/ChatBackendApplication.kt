package ch.dukex.chat.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class ChatBackendApplication

fun main(args: Array<String>) {
    runApplication<ChatBackendApplication>(*args)
}
