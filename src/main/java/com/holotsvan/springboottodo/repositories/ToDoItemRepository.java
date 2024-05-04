package com.holotsvan.springboottodo.repositories;

import com.holotsvan.springboottodo.models.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem,Long> {
}
