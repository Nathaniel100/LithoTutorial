package io.github.ginger.lithotutorial.lithography.sections

import com.facebook.litho.annotations.Prop
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.annotations.OnViewportChanged
import com.facebook.litho.sections.common.SingleComponentSection
import io.github.ginger.lithotutorial.lithography.components.LoadingComponent
import io.github.ginger.lithotutorial.lithography.data.Decade
import io.github.ginger.lithotutorial.lithography.data.Fetcher

@GroupSectionSpec
object LithoFeedSectionSpec {

  @OnCreateChildren
  fun onCreateChildren(
      c: SectionContext,
      @Prop decades: List<Decade>,
      @Prop loading: Boolean): Children {
    val children = Children.create()
    val decadeSections = decades.map {
      DecadeSection.create(c)
          .decade(it)
          .key("${it.year}")
          .build()
    }
    children.child(decadeSections)
    if (loading) {
      children.child(
          SingleComponentSection.create(c)
              .component(LoadingComponent.create(c).build())
      )
    }
    return children.build()
  }

  @OnViewportChanged
  fun onViewportChanged(c: SectionContext,
      firstVisible: Int,
      lastVisible: Int,
      totalCount: Int,
      fistFullyVisible: Int,
      lastFullyVisible: Int,
      @Prop fetcher: Fetcher,
      @Prop decades: List<Decade>) {
    val threshold = 2
    if (totalCount - lastVisible < threshold) {
      fetcher(decades.last().year)
    }

  }
}