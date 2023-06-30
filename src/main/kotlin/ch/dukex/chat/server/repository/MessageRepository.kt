package ch.dukex.chat.server.repository

import ch.dukex.chat.server.model.Message
import ch.dukex.chat.server.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<Message, Long> {
    fun findAllBySender(sender: Person): List<Message>

    fun findAllByReceiver(receiver: Person): List<Message>

    fun findAllByReceiverOrSender(receiver: Person, sender: Person): List<Message>
}