package ch.dukex.chat.server.controller

import ch.dukex.chat.server.model.Person
import ch.dukex.chat.server.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/persons")
@RequestMapping("api/persons")
@RestController
class PersonController(
    val service: PersonService
) {
    @GetMapping
    fun getAllPeople(): List<Person> = service.getAll()

    @GetMapping("/{id}")
    fun getPerson(@PathVariable id: Long): Person = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun savePerson(@RequestBody person: Person) = service.create(person)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun deletePerson(@PathVariable id: Long) = service.remove(id)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updatePerson(@PathVariable id: Long, @RequestBody person: Person): Person = service.update(id, person)
}