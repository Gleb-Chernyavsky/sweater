package com.example.demo.repos;

import com.example.demo.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessagesRepos extends CrudRepository<Message, Integer> {
    List<Message> findByTag(String tag);
}
