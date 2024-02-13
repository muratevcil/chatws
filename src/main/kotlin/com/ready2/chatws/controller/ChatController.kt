package com.ready2.chatws.controller

import com.ready2.chatws.business.abstract.ChatMessageService
import com.ready2.chatws.models.concrete.ChatMessage
import com.ready2.chatws.models.concrete.ChatNotification
import lombok.RequiredArgsConstructor
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
@RequiredArgsConstructor
class ChatController {
    @Autowired
    lateinit var chatMessageService: ChatMessageService;
    @Autowired
    lateinit var messagingTemplate: SimpMessagingTemplate;

    @MessageMapping("/chat")
    fun processMessage(@Payload chatMessage: ChatMessage){
        val savedMessage:ChatMessage = chatMessageService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
            chatMessage.recipientId.toString(),"/queue/messages",
            ChatNotification(
                savedMessage.id.toString(),
                savedMessage.senderId.toString(),
                savedMessage.recipientId.toString(),
                savedMessage.content.toString(),
            )
        )
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    fun findChatMessages(
        @PathVariable("senderId") senderId:String,
        @PathVariable("recipientId") recipientId:String,
    ):ResponseEntity<List<ChatMessage>>{
          return ResponseEntity.ok(chatMessageService.findChatMessages(senderId,recipientId));
    }

}