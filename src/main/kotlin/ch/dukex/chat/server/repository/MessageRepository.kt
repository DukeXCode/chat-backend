package ch.dukex.chat.server.repository

import ch.dukex.chat.server.model.Message
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, Long>