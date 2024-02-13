package com.ready2.chatws.models.concrete

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.util.Date


@Getter
@Setter
@Data
@Entity
@Table(name = "\"chatmessage\"")
data class ChatMessage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var chatId: String?,
    val senderId: String?,
    val recipientId: String?,
    val content: String?,
    val timestamp: Date?,
)