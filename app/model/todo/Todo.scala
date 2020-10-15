package model.todo

import lib.model.Todo._
import lib.model.Category

case class TodoValue (
  id:            Id,
  body:          String,
  note:          Option[String],
  status:        Short,
  category:      Option[Category]
)
