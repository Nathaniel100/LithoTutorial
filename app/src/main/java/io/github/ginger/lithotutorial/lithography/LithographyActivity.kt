package io.github.ginger.lithotutorial.lithography

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.facebook.litho.Component
import com.facebook.litho.LithoView
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.widget.RecyclerCollectionComponent
import io.github.ginger.lithotutorial.NavigatableDemoActivity
import io.github.ginger.lithotutorial.lithography.data.DataFetcher
import io.github.ginger.lithotutorial.lithography.data.DecadeViewModel
import io.github.ginger.lithotutorial.lithography.data.Model
import io.github.ginger.lithotutorial.lithography.sections.LithoFeedSection


class LithographyActivity : NavigatableDemoActivity() {

  private val sectionContext: SectionContext by lazy { SectionContext(this) }
  private val lithoView: LithoView by lazy { LithoView(sectionContext) }
  private val viewModel: DecadeViewModel by lazy {
    ViewModelProviders.of(this).get(DecadeViewModel::class.java)
  }
  private val fetcher: DataFetcher by lazy { DataFetcher(viewModel.model) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    viewModel.model.observe(this, Observer { setList(it) })
    setContentView(lithoView)
  }

  private fun setList(model: Model?) {
    model?.let {
      if (lithoView.componentTree == null) {
        lithoView.setComponent(createRecyclerComponent(it))
      } else {
        lithoView.setComponentAsync(createRecyclerComponent(it))
      }
    }
  }

  private fun createRecyclerComponent(model: Model): Component =
      RecyclerCollectionComponent.create(sectionContext)
          .section(LithoFeedSection.create(sectionContext)
              .decades(model.decades)
              .fetcher(fetcher)
              .loading(model.isLoading)
              .build())
          .disablePTR(true)
          .build()


}