package io.github.ginger.lithotutorial.lithography.data

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

data class DecadeViewModel(
  val model: MutableLiveData<Model> = MutableLiveData<Model>().apply {
    value = Model(DataCreator.createPageOfData(0), false)
  }
) : ViewModel()