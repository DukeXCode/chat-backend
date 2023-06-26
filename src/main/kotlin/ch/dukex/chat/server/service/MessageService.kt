package ch.dukex.chat.server.service

import ch.dukex.chat.server.model.Message
import ch.dukex.chat.server.model.Person
import ch.dukex.chat.server.repository.MessageRepository
import ch.dukex.chat.server.repository.PersonRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

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
        return if (personsExist(message)) repository.save(message)
        else throw ResponseStatusException(HttpStatus.BAD_REQUEST)
    }

    fun remove(id: Long) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Long, message: Message): Message {
        if (!repository.existsById(id)) throw ResponseStatusException(HttpStatus.NOT_FOUND)
        if (!personsExist(message)) throw ResponseStatusException(HttpStatus.BAD_REQUEST)
        message.id = id
        return repository.save(message)
    }

    fun receivedMessages(person: Person): List<Message> = repository.findAllByReceiver(person)

    fun sentMessages(person: Person): List<Message> = repository.findAllBySender(person)

    private fun personsExist(message: Message): Boolean {
        return personRepository.existsById(message.sender.id) && personRepository.existsById(message.receiver.id)
    }
}