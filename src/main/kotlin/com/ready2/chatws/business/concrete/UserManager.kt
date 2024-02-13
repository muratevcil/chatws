package com.ready2.chatws.business.concrete

import com.ready2.chatws.business.abstract.UserService
import com.ready2.chatws.models.concrete.Status
import com.ready2.chatws.models.concrete.User
import com.ready2.chatws.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserManager:UserService {
    @Autowired
    lateinit var userRepository:UserRepository;

    override fun saveUser(user: User){
        userRepository.save(user);
    };

    override fun disconnect(user: User){
        var storedUser = userRepository.findById(user.nickName.toString())
            .orElse(null);
        if(storedUser != null){
            storedUser.status = Status.OFFLINE;
            userRepository.save(storedUser);
        }
    }

    override fun findUser(status:Status): List<User> {
        var listOfUsers:List<User> = userRepository.findUsersByStatus(status);
        return listOfUsers;
    }
}