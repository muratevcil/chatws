package com.ready2.chatws.business.concrete

import com.ready2.chatws.business.abstract.ChatRoomService
import com.ready2.chatws.models.concrete.ChatMessage
import com.ready2.chatws.models.concrete.ChatRoom
import com.ready2.chatws.repository.ChatRoomRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ChatRoomManager:ChatRoomService {
    @Autowired
    lateinit var chatRoomRepository: ChatRoomRepository;
    override fun getChatRoomId(
        senderId: String,
        recipientId: String,
        createNewRoomIfNotExists: Boolean
    ): String? {
        val chatRoom = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId)
        if (chatRoom != null) {
            return chatRoom.chatId
        } else {
            if (createNewRoomIfNotExists) {
                return createChatId(senderId, recipientId)
            }
            return null // Return null instead of an empty string
        }
    }
    override fun createChatId(senderId: String, recipientId: String): String {
        val chatId = "$senderId _ $recipientId"
        val senderRecipient = ChatRoom(
            id = UUID.randomUUID().toString(),
            chatId = chatId,
            senderId = senderId,
            recipientId = recipientId
        )
        val recipientSender = ChatRoom(
            id = UUID.randomUUID().toString(),
            chatId = chatId,
            senderId = recipientId,
            recipientId = senderId
        )

        chatRoomRepository.save(senderRecipient)
        chatRoomRepository.save(recipientSender)

        return chatId
    }


}