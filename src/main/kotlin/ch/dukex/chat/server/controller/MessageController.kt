package ch.dukex.chat.server.controller

import ch.dukex.chat.server.model.Message
import ch.dukex.chat.server.service.MessageService
import ch.dukex.chat.server.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RequestMapping("/messages")
@RestController
class MessageController(val service: MessageService, val personService: PersonService) {
    @GetMapping
    fun allMessages(): List<Message> = service.getAll()

    @GetMapping("/{id}")
    fun getMessage(@PathVariable id: Long): Message = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createMessage(@RequestBody message: Message): Message = service.create(message)

    @DeleteMapping("/{id}")
    fun deletePerson(@PathVariable id: Long) = service.remove(id)

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun updatePerson(@PathVariable id: Long, @RequestBody message: Message): Message = service.update(id, message)

    @GetMapping("/receiver/{id}")
    fun getReceivedMessages(@PathVariable id: Long): List<Message> = service.receivedMessages(personService.getById(id))
    
    @GetMapping("/sender/{id}")
    fun getSentMessages(@PathVariable id: Long): List<Message> = service.sentMessages(personService.getById(id))
}