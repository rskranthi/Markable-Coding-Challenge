package com.markable.repositories;

import com.markable.domain.*;
import org.springframework.data.repository.CrudRepository;

//Repository for Database Connection
public interface MessageRepository extends CrudRepository<Message, Integer>{
}
