package ch.dukex.chat.server.service

import ch.dukex.chat.server.model.Message
import ch.dukex.chat.server.model.Person
import ch.dukex.chat.server.repository.MessageRepository
import ch.dukex.chat.server.repository.PersonRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant

@Service
class MessageService(
    val repository: MessageRepository,
    val personRepository: PersonRepository
) {
    fun getAll(): List<Message> = repository.findAll()

    fun getById(id: Long): Message {
        return repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun create(message: Message): Message {
        return if (personsExist(message)) {
            message.createdAt = Instant.now()
            repository.save(message)
        }
        else throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Receiver or sender does not exist.")
    }

    fun remove(id: Long) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Long, message: Message): Message {
        val oldMessage = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        if (!personsExist(message)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Receiver or sender does not exist.")
        }
        message.id = id
        message.createdAt = oldMessage.createdAt
        return repository.save(message)
    }

    fun receivedMessages(person: Person): List<Message> = repository.findAllByReceiver(person)

    fun sentMessages(person: Person): List<Message> = repository.findAllBySender(person)

    fun receivedOrSentMessages(person: Person): List<Message> = repository.findAllByReceiverOrSender(person, person)

    private fun personsExist(message: Message): Boolean {
        return personRepository.existsById(message.sender.id) && personRepository.existsById(message.receiver.id)
    }
}