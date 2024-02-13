package com.ready2.chatws.repository

import com.ready2.chatws.models.concrete.Status
import com.ready2.chatws.models.concrete.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User,String> {
    fun findUsersByStatus(status: Status):List<User>;
}