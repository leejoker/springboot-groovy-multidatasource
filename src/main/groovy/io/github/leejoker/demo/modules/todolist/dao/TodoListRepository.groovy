package io.github.leejoker.demo.modules.todolist.dao

import io.github.leejoker.demo.modules.todolist.entity.TodoList
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoListRepository extends CrudRepository<TodoList, Long> {

}
