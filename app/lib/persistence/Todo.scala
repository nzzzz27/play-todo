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

abstract case class TodoRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  todoTable:  TodoTable,
)(implicit ec: ExecutionContext)
  extends BaseController with HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  def getAll(): Future[Seq[Todo]] = {
    db.run {
      todoTable.todo.result
    }
  }

}
