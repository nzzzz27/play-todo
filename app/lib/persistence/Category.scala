package lib.persistence

import slick.jdbc.MySQLProfile.api._
import java.time.LocalDateTime
import scala.reflect.ClassTag

import javax.inject.{ Inject, Singleton }

import play.api.mvc.{
  Request,
  AnyContent,
}

import play.api.db.slick.{
  DatabaseConfigProvider,
  HasDatabaseConfigProvider,
}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import lib.model.Category
import CategoryTable.category

class CategoryRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  def getAll(): Future[Seq[Category]] = {
    db.run {
      category.result
    }
  }

  def get(id: Long): Future[Option[Category]] = {
    db.run {
      category.result.map(_.filter(_.id == id).headOption)
    }
  }
}

case class CategoryTable(tag: Tag) extends Table[Category](tag, "category") {
  // Columns
  /* @1 */ def id          = column[Category.Id]    ("ID", O.PrimaryKey, O.AutoInc)
  /* @2 */ def name        = column[String]         ("NAME")
  /* @3 */ def color       = column[Short]          ("COLOR")

  type TableElementTuple = (
    Option[Category.Id],
    String,
    Short
  )

  // DB <=> Scala の相互のmapping定義
  def * = (id.?, name, color) <> (
    // Tuple(table) => Model
    (t: TableElementTuple) => Category(
      t._1, t._2, t._3
    ),
    // Model => Tuple(table)
    (v: Category) => Category.unapply(v).map { t => (
      t._1, t._2, t._3
    )}
  )
}

object CategoryTable {
  val category: TableQuery[CategoryTable] = TableQuery[CategoryTable]
}
