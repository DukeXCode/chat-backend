package ch.dukex.chat.server.service

import ch.dukex.chat.server.repository.PersonRepository
import ch.dukex.chat.server.model.Person
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PersonService(val repository: PersonRepository) {
    fun getAll(): List<Person> = repository.findAll()

    fun getById(id: Long): Person = repository.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun create(person: Person) = repository.save(person)

    fun remove(id: Long) {
        if (repository.existsById(id)) repository.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    fun update(id: Long, person: Person): Person {
        return if (repository.existsById(id)) {
            person.id = id
            repository.save(person)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }
}