package com.holotsvan.springboottodo.controllers;

import com.holotsvan.springboottodo.models.ToDoItem;
import com.holotsvan.springboottodo.services.ToDoItemService;
import com.sun.nio.sctp.IllegalReceiveException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ToDoFormController {

    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/create-todo")
    public String sohwCreateForm(ToDoItem toDoItem) {
        return "new-todo-item";
    }

    @PostMapping("/todo")
    public String createToDoItem(@Valid ToDoItem toDoItem, BindingResult result, Model model) {

        ToDoItem item = new ToDoItem();
        item.setDescription(toDoItem.getDescription());
        item.setIsCompleted(toDoItem.getIsCompleted());

        toDoItemService.save(toDoItem);

        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id, Model model) {
        ToDoItem toDoItem = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalReceiveException("Todo id:" + id + " not found"));

        toDoItemService.delete(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        ToDoItem toDoItem = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalReceiveException("Todo id:" + id + " not found"));

        model.addAttribute("toDoItem",toDoItem);

        return "edit-todo-item";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid ToDoItem toDoItem, BindingResult bindingResult){
        ToDoItem item = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalReceiveException("Todo id:" + id + " not found"));

        item.setIsCompleted(toDoItem.getIsCompleted());
        item.setDescription(toDoItem.getDescription());
        toDoItemService.save(item);
        return "redirect:/";
    }
}
