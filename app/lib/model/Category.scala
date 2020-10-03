package lib.model

import java.time.LocalDateTime

case class Category(
  id:          Option[Long],
  name:        String,
  color:       Option[Short],
  updated_at:  LocalDateTime,
  created_at:  LocalDateTime,
)
