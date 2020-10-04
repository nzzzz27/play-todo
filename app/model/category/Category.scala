package model.category

import lib.model.Category

case class CategoryValue(
  id:       Category.Id,
  name:     String,
  color:    Short,
)
