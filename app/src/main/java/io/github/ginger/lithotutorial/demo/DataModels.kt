package io.github.ginger.lithotutorial.demo

import io.github.ginger.lithotutorial.lithography.LithographyActivity


object DataModels {
  val DATA_MODELS = listOf(
    DemoListDataModel("Lithography", LithographyActivity::class.java)
  )

  fun getDataModels(indices: IntArray?): List<DemoListDataModel> {
    if (indices == null) return DATA_MODELS
    var dataModels : List<DemoListDataModel> = DATA_MODELS
    indices.forEach { dataModels = dataModels[it].datamodels ?: dataModels }
    return dataModels
  }
}