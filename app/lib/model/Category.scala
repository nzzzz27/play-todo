package lib.model

import Category._
case class Category(
  id:          Option[Category.Id],
  name:        String,
  color:       Option[Short]
)

/*
 * @NOTE
 * 同一のCategory IDを使いたいので、専用の型を作る
 */
object Category {
  type Id = Long

  sealed abstract class EnumColor(val code: Short, val name: String, val color: String)
  object Color {
    case object IS_NOTYET     extends EnumColor(code = 0, name = "赤", color = "red")
    case object IS_ONPROGRESS extends EnumColor(code = 1, name = "青", color = "blue")
    case object IS_DONE       extends EnumColor(code = 2, name = "黄", color = "yellow")
  }

}
