package json.writes

import play.api.libs.json._
import play.api.libs.functional.syntax._

import lib.model.Todo._
import lib.model.{ Todo, Category }

// slaca -> json
case class JsValueTodo(
  id:            Option[Id],
  body:          String,
  note:          Option[String],
  status:        Short,
  category:      Option[JsValueCategory]
)

object JsValueTodo {

  // @NOTE
  // JsPath の代わりに __ と書いてもOK
  implicit val jsValueTodo: Writes[JsValueTodo] = (
    (JsPath \ "id"           ).write[Option[Long]]     and
    (JsPath \ "body"         ).write[String]           and
    (JsPath \ "note"         ).write[Option[String]]   and
    (JsPath \ "status"       ).write[Short]            and
    ( __    \ "category"     ).write[Option[JsValueCategory]]
  )(unlift(JsValueTodo.unapply))

  def create(todoSeq: Seq[Todo], categorySeq: Seq[Category]) = {
    todoSeq.map { todo =>
      JsValueTodo(
        todo.id,
        todo.body,
        todo.note,
        todo.status,
        category = (categorySeq.find(_.id == Some(todo.id)) match {
          case Some(ca) => Some(JsValueCategory(
             id    = ca.id,
             name  = ca.name,
             color = ca.color
           ))
          case None => None
        })
      )
    }
  }

}
