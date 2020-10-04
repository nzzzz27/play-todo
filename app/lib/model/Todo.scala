package lib.model

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
}
