package lib.persistence.db

import slick.jdbc.MySQLProfile.api._
import java.time.LocalDateTime
import scala.reflect.ClassTag

import lib.model.Category

// Table rows
// https://scala-slick.org/doc/3.3.1/schemas.html#table-rows
class CategoryTable(tag: Tag) extends Table[Category](tag, "category") {

  // Columns
  /* @1 */ def id          = column[Long]           ("ID", O.PrimaryKey, O.AutoInc)
  /* @2 */ def name        = column[String]         ("NAME")
  /* @3 */ def color       = column[Option[Short]]  ("COLOR")
  /* @4 */ def updated_at  = column[LocalDateTime]  ("UPDATED_AT")
  /* @5 */ def created_at  = column[LocalDateTime]  ("CREATED_AT")

  type TableElementTuple = (
    Option[Long],
    String,
    Option[Short],
    LocalDateTime,
    LocalDateTime,
  )

  // DB <=> Scala の相互のmapping定義
  def * = (id.?, name, color, updated_at, created_at) <> (
    // Tuple(table) => Model
    (t: TableElementTuple) => Category(
      t._1, t._2, t._3, t._4, t._5
    ),
    // Model => Tuple(table)
    (v: Category) => Category.unapply(v).map { t => (
      t._1, t._2, t._3, LocalDateTime.now(), LocalDateTime.now()
    )}
  )
}
