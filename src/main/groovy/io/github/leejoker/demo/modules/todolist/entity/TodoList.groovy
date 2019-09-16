package io.github.leejoker.demo.modules.todolist.entity

import javax.persistence.*

@Entity
@Table(name = "todolist")
class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id
    @Column(name = "serialnum")
    private int serialNum
    @Column(name = "content")
    private String content
    @Column(name = "status")
    private int status
}
