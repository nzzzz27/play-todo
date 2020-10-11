package model.todo

import lib.model.Todo._

case class TodoValue (
  id:            Id,
  body:          String,
  note:          Option[String],
  status:        Short,
  categoryName:  String,
)
