package io.github.ginger.lithotutorial.demo

import android.os.Bundle
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import io.github.ginger.lithotutorial.NavigatableDemoActivity


class DemoListActivity : NavigatableDemoActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val indices = intent.getIntArrayExtra(INDICES)
    val dataModels = DataModels.getDataModels(indices)
    val componentContext = ComponentContext(this)
    setContentView(
      LithoView.create(
        this,
        DemoListComponent.create(componentContext).dataModels(dataModels).parentIndices(indices).build()
      )
    )
  }


  companion object {
    const val INDICES = "INDICES"
  }
}