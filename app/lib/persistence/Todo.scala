package lib.persistence

import javax.inject.{ Inject, Singleton }

import play.api.mvc.{
  ControllerComponents,        // ほとんどのControllerが依存している関数
  BaseController,              // Actionを使えるようにする
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
import lib.persistence.db.TodoTable

abstract class TodoRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
)(implicit ec: ExecutionContext)
  extends BaseController with HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  def getAll() = {
    val q = for (c <- TodoTable.todo) yield c.id
    val a = q.result
    val f: Future[Seq[String]] = db.run(a)
    
    f.onComplete {
      case Success(s) => println(s"Result: $s")
      case Failure(t) => t.printStackTrace()
    }
  }

}
