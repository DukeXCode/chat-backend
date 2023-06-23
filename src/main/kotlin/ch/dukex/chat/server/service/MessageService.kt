package ch.dukex.chat.server.service

import ch.dukex.chat.server.model.Message
import ch.dukex.chat.server.repository.MessageRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MessageService(val repository: MessageRepository) {
    fun getAll(): List<Message> = repository.findAll()

    fun getById(id: Long): Message = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(message: Message) = repository.save(message)

    fun remove(id: Long) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Long, message: Message): Message {
        return if (repository.existsById(id)) {
            message.id = id
            repository.save(message)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}