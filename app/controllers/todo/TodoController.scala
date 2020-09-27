package controllers

import javax.inject.{ Inject, Singleton }

import play.api.mvc.{
  ControllerComponents,        // ほとんどのControllerが依存している関数
  BaseController,              // Actionを使えるようにする
  Request,                     // httpリクエストを使えるようにする
  AnyContent,                  // リクエストコンテンツタイプに応じたリクエストボディを生成
}
import play.api.i18n.I18nSupport

import scala.concurrent.ExecutionContext

import lib.persistence.{ TodoRepository }

@Singleton
class TodoController @Inject()(
  val controllerComponents: ControllerComponents,
  todoRepo: TodoRepository,
)(implicit ec: ExecutionContext)
  extends BaseController with I18nSupport {

  def showAll() = Action { implicit  req: Request[AnyContent] =>
    todoRepo.getAll()
    Ok(views.html.index())
  }

}
