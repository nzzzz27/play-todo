package lib.model

case class Category(
  id:          Option[Category.Id],
  name:        String,
  color:       Short,
)

/*
 * @NOTE
 * 同一のCategory IDを使いたいので、専用の型を作る
 */
object Category {
  type Id = Long
}
