package json.writes

import play.api.libs.json._
import play.api.libs.functional.syntax._

import lib.model.Todo._
import lib.model.Category

// slaca -> json
case class JsValueTodo(
  id:            Id,
  body:          String,
  note:          Option[String],
  status:        Short,
  categoryId:    Option[Long],
  categoryName:  Option[String],
  categoryColor: Option[Short]
)

object JsValueTodo {

  implicit val jsValueTodo: Writes[JsValueTodo] = (
    (JsPath \ "id"           ).write[Long]             and
    (JsPath \ "body"         ).write[String]           and
    (JsPath \ "note"         ).write[Option[String]]   and
    (JsPath \ "status"       ).write[Short]            and
    (JsPath \ "categoryId"   ).write[Option[Long]]     and
    (JsPath \ "categoryName" ).write[Option[String]]     and
    (JsPath \ "categoryColor").write[Option[Short]]
  )

  /*  @NOTE
   *  case classと異なる形のWritesが欲しい場合は、整形用のメソッドの自作が必要
   */

}
