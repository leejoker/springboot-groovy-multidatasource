package io.github.leejoker.demo.modules.todolist.controller

import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import io.github.leejoker.demo.modules.todolist.dao.TodoListRepository
import io.github.leejoker.demo.modules.todolist.entity.TodoList
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todo")
@Transactional(transactionManager = "todoListTransactionManager")
class TodoListController {
    @Autowired
    private TodoListRepository todoListRepository

    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    def add(@RequestBody TodoList param) {
        def json = new JSONObject()
        try {
            todoListRepository.save(param)
            json.put("status", "ok")
            json.put("msg", "")
        } catch (Exception e) {
            json.put("status", "bad")
            json.put("msg", e.printStackTrace())
        } finally {
            return json.toJSONString()
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    def list() {
        Iterable<TodoList> todolist = todoListRepository.findAll()
        JSONArray array = new JSONArray()
        todolist.each {
            array.add(JSONObject.parseObject(JSONObject.toJSONString(it)))
        }
        return array.toJSONString()
    }

    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    def del(@RequestBody TodoList param) {
        def json = new JSONObject()
        try {
            todoListRepository.delete(param)
            json.put("status", "ok")
            json.put("msg", "")
        } catch (Exception e) {
            json.put("status", "bad")
            json.put("msg", e.printStackTrace())
        } finally {
            return json.toJSONString()
        }
    }
}
