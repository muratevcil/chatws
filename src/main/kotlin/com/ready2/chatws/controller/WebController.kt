package com.ready2.chatws.controller

import com.ready2.chatws.business.abstract.UserService
import com.ready2.chatws.business.concrete.UserManager
import com.ready2.chatws.models.concrete.Status
import com.ready2.chatws.models.concrete.User
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Controller
@RequiredArgsConstructor
class WebController {

    @Autowired
    lateinit var userService:UserService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/topic") //sends notifications if there is another user joining to chat
    fun addUser( @Payload user: User):User{
        userService.saveUser(user);
        return user;
    }

    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/topic")
    fun disconnect( @Payload user:User ):User{
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    fun findConnectedusers():ResponseEntity<List<User>>{
        return ResponseEntity.ok(userService.findUser(Status.ONLINE));
    }
}