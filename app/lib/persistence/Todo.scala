package lib.persistence

import slick.jdbc.MySQLProfile.api._
import java.time.LocalDateTime
import scala.reflect.ClassTag

import javax.inject.{ Inject, Singleton }

import play.api.mvc.{
  ControllerComponents,        // ほとんどのControllerが依存している関数
  BaseControllerHelpers,
  Request,                     // httpリクエストを使えるようにする
  AnyContent,                  // リクエストコンテンツタイプに応じたリクエストボディを生成
}

import play.api.db.slick.{
  DatabaseConfigProvider,
  HasDatabaseConfigProvider,   // DIでSlickデータベースとプロファイルを使用するために必要
}
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }

import lib.model.Todo
// import lib.persistence.db.TodoTable

import TodoTable.todo
class TodoRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  def getAll(): Future[Seq[Todo]] = {
    db.run {
      todo.result
    }
  }
}

case class TodoTable(tag: Tag) extends Table[Todo](tag, "todo") {
  // Columns
  /* @1 */ def id          = column[Long]           ("ID", O.PrimaryKey, O.AutoInc)
  /* @2 */ def body        = column[String]         ("BODY")
  /* @3 */ def note        = column[Option[String]] ("NOTE")
  /* @4 */ def category_id = column[Long]           ("CATEGORY_ID")
  /* @5 */ def status      = column[Short]          ("STATUS")

  type TableElementTuple = (
    Option[Long],
    String,
    Option[String],
    Long,
    Short,
  )

  // DB <=> Scala の相互のmapping定義
  def * = (id.?, body, note, category_id, status) <> (
    // Tuple(table) => Model
    (t: TableElementTuple) => Todo(
      t._1, t._2, t._3, t._4, t._5
    ),
    // Model => Tuple(table)
    (v: Todo) => Todo.unapply(v).map { t => (
      t._1, t._2, t._3, t._4, t._5
    )}
  )
}

object TodoTable {
  val todo: TableQuery[TodoTable] = TableQuery[TodoTable]
}

// slickの仕様でLocalDateTimeがうまく動かないので、一旦コメントアウト
// class TodoTable(tag: Tag) extends Table[Todo](tag, "todo") {
//
//   // Columns
//   /* @1 */ def id          = column[Long]           ("ID", O.PrimaryKey, O.AutoInc)
//   /* @2 */ def body        = column[String]         ("BODY")
//   /* @3 */ def note        = column[Option[String]] ("NOTE")
//   /* @4 */ def category_id = column[Long]           ("CATEGORY_ID")
//   /* @5 */ def status      = column[Short]          ("STATUS")
//   /* @6 */ def updated_at  = column[LocalDateTime]  ("UPDATED_AT")
//   /* @7 */ def created_at  = column[LocalDateTime]  ("CREATED_AT")
//
//   type TableElementTuple = (
//     Option[Long],
//     String,
//     Option[String],
//     Long,
//     Short,
//     LocalDateTime,
//     LocalDateTime,
//   )
//
//   // DB <=> Scala の相互のmapping定義
//   def * = (id.?, body, note, category_id, status, updated_at, created_at) <> (
//     // Tuple(table) => Model
//     (t: TableElementTuple) => Todo(
//       t._1, t._2, t._3, t._4, t._5, t._6, t._7
//     ),
//     // Model => Tuple(table)
//     (v: Todo) => Todo.unapply(v).map { t => (
//       t._1, t._2, t._3, t._4, t._5, LocalDateTime.now(), t._7
//     )}
//   )
//
//   // TodoTableへアクセスできるようにする
//   val todo: TableQuery[TodoTable] = TableQuery[TodoTable]
// }
//
