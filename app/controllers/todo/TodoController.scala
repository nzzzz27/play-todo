package controllers

import javax.inject.{ Inject, Singleton }

import play.api.mvc.{
  ControllerComponents,        // ほとんどのControllerが依存している関数
  BaseController,              // Actionを使えるようにする
  Request,                     // httpリクエストを使えるようにする
  AnyContent,                  // リクエストコンテンツタイプに応じたリクエストボディを生成
}
import play.api.i18n.I18nSupport
import play.api.libs.json._

import scala.concurrent.ExecutionContext

import lib.persistence.TodoRepository
import lib.model.Category
import lib.persistence.CategoryRepository


@Singleton
class TodoController @Inject()(
  val controllerComponents: ControllerComponents,
  todoRepo: TodoRepository,
  categoryRepo: CategoryRepository,
)(implicit ec: ExecutionContext)
  extends BaseController with I18nSupport {

  // Todo一覧
  def showAll() = Action async {
    for {
      todoSeq      <- todoRepo.getAll()
      categorySeq  <- categoryRepo.getAll()
    } yield {
      import json.writes._

      val jsValueTodo = todoSeq.map { todo =>

        JsValueTodo(
          todo.id.get,
          todo.body,
          todo.note,
          category = categorySeq.filter(_.id == todo.categoryId).headOption 
        )
      }

      Ok(Json.toJson(jsValueTodo))
    }
  }


}
