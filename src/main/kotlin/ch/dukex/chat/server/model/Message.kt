package ch.dukex.chat.server.model

import jakarta.persistence.*

@Entity
data class Message(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var text: String,
        @ManyToOne
        var sender: Person,
        @ManyToOne
        var receiver: Person
)
