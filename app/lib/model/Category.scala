package lib.model

import Category._
case class Category(
  id:          Option[Category.Id],
  name:        String,
)

/*
 * @NOTE
 * 同一のCategory IDを使いたいので、専用の型を作る
 */
object Category {
  type Id = Long
}
