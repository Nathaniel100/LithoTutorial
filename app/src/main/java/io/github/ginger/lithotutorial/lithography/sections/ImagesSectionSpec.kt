package io.github.ginger.lithotutorial.lithography.sections

import com.facebook.litho.annotations.FromEvent
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.common.DataDiffSection
import com.facebook.litho.sections.common.RenderEvent
import com.facebook.litho.widget.ComponentRenderInfo
import com.facebook.litho.widget.RenderInfo
import io.github.ginger.lithotutorial.lithography.components.SingleImageComponent

@GroupSectionSpec
object ImagesSectionSpec {
  @OnCreateChildren
  fun onCreateChildren(c: SectionContext, @Prop images: List<String>): Children =
    Children.create()
      .child(
        DataDiffSection.create<String>(c)
          .data(images)
          .renderEventHandler(ImagesSection.onRender(c))
      )
      .build()

  @OnEvent(RenderEvent::class)
  fun onRender(c: SectionContext, @FromEvent model: String): RenderInfo =
    ComponentRenderInfo.create()
      .component(
        SingleImageComponent.create(c)
          .image(model)
          .imageAspectRatio(2f)
          .build()
      )
      .build()
}