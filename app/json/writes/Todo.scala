package json.writes

import play.api.libs.json.Json
import lib.model.Todo._

// slaca -> json
case class JsValueTodo(
  id:            Id,
  body:          String,
  note:          Option[String],
  status:        Short,
  categoryName:  String,
)

object JsValueTodo {

  implicit val jsValueTodo = Json.writes[JsValueTodo]

}