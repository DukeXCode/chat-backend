package ch.dukex.chat.server.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.time.Instant

@Entity
data class Message(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,
        var text: String,
        @ManyToOne
        var sender: Person,
        @ManyToOne
        var receiver: Person,
        @NotNull
        var createdAt: Instant?
)
