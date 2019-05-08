package io.github.ginger.lithotutorial.demo

data class DemoListDataModel(
  val name: String,
  val kClass: Class<*>? = null,
  val datamodels: List<DemoListDataModel>? = null
)