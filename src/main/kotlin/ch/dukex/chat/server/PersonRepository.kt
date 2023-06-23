package ch.dukex.chat.server

import ch.dukex.chat.server.model.Person
import org.springframework.data.jpa.repository.JpaRepository


interface PersonRepository : JpaRepository<Person, Long>