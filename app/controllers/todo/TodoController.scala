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
import model.todo.{ TodoValue, ViewValueTodo }
import model.category.{ CategoryValue }
import lib.model.Category
import lib.persistence.CategoryRepository

@Singleton
class TodoController @Inject()(
  val controllerComponents: ControllerComponents,
  todoRepo: TodoRepository,
  categoryRepo: CategoryRepository,
)(implicit ec: ExecutionContext)
  extends BaseController with I18nSupport {

  /*
   *  Todo 一覧ページ
   */
  def showAll() = Action async { implicit  req: Request[AnyContent] =>
    for {
      todoSeq      <- todoRepo.getAll()
      categorySeq  <- categoryRepo.getAll()
    } yield {

      val todos: Seq[TodoValue] = todoSeq.map { todo =>

        val matchedCategoryName: Option[String] = categorySeq.map(c => (c.id, c.name)).toMap.get(Some(todo.categoryId))

        TodoValue(
          todo.id.get,
          todo.body,
          todo.note,
          todo.status,
          categoryName  = matchedCategoryName.get,
        )
      }

      val vv = ViewValueTodo(
        todos = todos
      )
      println(vv)

      Ok(views.html.todo.list())
    }


  }

}
