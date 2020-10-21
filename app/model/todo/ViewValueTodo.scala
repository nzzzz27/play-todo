package model.todo

case class ViewValueTodo (
 css:   Seq[String] = Seq("main.css"),
 js:    Seq[String] = Seq("main.js"),
 todos: Seq[Option[TodoValue]]
)
