package io.github.ginger.lithotutorial.lithography.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


data class DecadeViewModel(
  val model: MutableLiveData<Model> = MutableLiveData<Model>().apply {
    value = Model(DataCreator.createPageOfData(0), false)
  }
) : ViewModel()