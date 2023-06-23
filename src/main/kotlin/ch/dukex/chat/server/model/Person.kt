package ch.dukex.chat.server.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var firstName: String,
    var lastName: String,
) {
    override fun toString(): String {
        return "Person[id=$id, firstName=$firstName, lastName=$lastName]"
    }
}