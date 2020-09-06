package lib.persistence

import javax.inject.{ Inject, Singleton }

// ほとんどのControllerが依存している関数
import play.api.mvc.{ ControllerComponents }

import play.api.db.slick.{
  DatabaseConfigProvider,
  HasDatabaseConfigProvider,   // DIでSlickデータベースとプロファイルを使用するために必要
}
import slick.jdbc.JdbcProfile
import scala.concurrent.ExecutionContext

@Singleton
class TodoRrepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
)(implicit ec: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._
}
