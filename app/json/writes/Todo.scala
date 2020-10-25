package json.writes

import play.api.libs.json._
import play.api.libs.functional.syntax._

import lib.model.Todo._
import lib.model.{ Todo, Category }

case class JsValueCategory(
  id:       Category.Id,
  name:     String,
  color:    Option[Short]
)

object JsValueCategory {

  implicit val jsValueCategory: Writes[JsValueCategory] = (
    (JsPath \ "id"        ).write[Long]     and
    (JsPath \ "name"      ).write[String]   and
    (JsPath \ "color"     ).write[Option[Short]] 
  )(unlift(JsValueCategory.unapply))

  /*  @NOTE
   *  case classと異なる形のWritesが欲しい場合は、整形用のメソッドの自作が必要
   */
}

// slaca -> json
case class JsValueTodo(
  id:            Option[Id],
  body:          String,
  note:          Option[String],
  status:        Short,
  category:      Option[Category]
)

object JsValueTodo {

  implicit val jsValueTodo: Writes[JsValueTodo] = (
    (JsPath \ "id"           ).write[Option[Long]]     and
    (JsPath \ "body"         ).write[String]           and
    (JsPath \ "note"         ).write[Option[String]]   and
    (JsPath \ "status"       ).write[Short]            and
    (JsPath \ "category"     ).write[Option[JsValueCategory]]
  )

  /*  @NOTE
   *  case classと異なる形のWritesが欲しい場合は、整形用のメソッドの自作が必要
   */

}
