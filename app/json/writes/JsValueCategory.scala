package json.writes

import play.api.libs.json._
import play.api.libs.functional.syntax._

import lib.model.Category._

case class JsValueCategory(
  id:       Option[Id],
  name:     String,
  color:    Option[Short]
)

object JsValueCategory {

  implicit val jsValueCategory: Writes[JsValueCategory] = (
    (JsPath \ "id"        ).write[Option[Long]]     and
    (JsPath \ "name"      ).write[String]           and
    (JsPath \ "color"     ).write[Option[Short]] 
  )(unlift(JsValueCategory.unapply))

}
