package lib.model

import java.time.LocalDateTime

case class Todo(
  id:          Option[Long],
  body:        String,
  note:        Option[String],
  category_id: Long,
  status:      Short,
)
