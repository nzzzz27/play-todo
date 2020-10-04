package model.todo

import lib.model.Todo
import lib.model.Category

case class TodoValue (
  id:            Todo.Id,
  body:          String,
  note:          Option[String],
  status:        Short,
  categoryName:  String,
  categoryColor: Short,
)
