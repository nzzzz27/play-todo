package lib.model

import Todo._
case class Todo(
  id:             Option[Todo.Id],
  body:           String,
  note:           Option[String],
  status:         Short,
  categoryId:     Category.Id,
)

/*
 * @NOTE
 * 同一のTodo IDを使いたいので、専用の型を作る
 */
object Todo {
  type Id = Long

  sealed abstract class EnumStatus(val code: Short, val name: String)
  object Status {
    case object IS_NOTYET     extends EnumStatus(code = 0, name = "未着手")
    case object IS_ONPROGRESS extends EnumStatus(code = 1, name = "進行中")
    case object IS_DONE       extends EnumStatus(code = 2, name = "完了")
  }

}
