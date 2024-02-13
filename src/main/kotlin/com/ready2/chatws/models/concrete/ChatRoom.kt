package com.ready2.chatws.models.concrete

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter


@Getter
@Setter
@Entity
@Table(name = "\"chatroom\"")
@Data
@Builder
data class ChatRoom (
    @Id
    val id: String,
    var chatId: String,
    var senderId: String,
    var recipientId: String,
)
