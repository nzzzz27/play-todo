package controllers

import javax.inject.{ Inject, Singleton }

import play.api.mvc.{
  ControllerComponents,        // ほとんどのControllerが依存している関数
  BaseController,
  Request,                     // httpリクエストを使えるようにする
  AnyContent,                  // リクエストコンテンツタイプに応じたリクエストボディを生成
}

import scala.concurrent.ExecutionContext

import lib.persistence._

@Singleton
class TodoController @Inject()(
  val controllerComponents: ControllerComponents
)(implicit ec: ExecutionContext)
  extends BaseController with play.api.i18n.I18nSupport {

    def showAll() = Action { implicit  req: Request[AnyContent] =>
    Ok(views.html.index())
  }

}
