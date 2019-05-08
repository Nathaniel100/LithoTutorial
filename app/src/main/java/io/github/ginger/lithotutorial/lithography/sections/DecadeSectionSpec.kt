package io.github.ginger.lithotutorial.lithography.sections

import com.facebook.litho.annotations.FromEvent
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.Prop
import com.facebook.litho.sections.Children
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.annotations.GroupSectionSpec
import com.facebook.litho.sections.annotations.OnCreateChildren
import com.facebook.litho.sections.common.DataDiffSection
import com.facebook.litho.sections.common.OnCheckIsSameItemEvent
import com.facebook.litho.sections.common.RenderEvent
import com.facebook.litho.sections.common.SingleComponentSection
import com.facebook.litho.widget.ComponentRenderInfo
import com.facebook.litho.widget.RenderInfo
import io.github.ginger.lithotutorial.lithography.components.DecadeSeperator
import io.github.ginger.lithotutorial.lithography.components.FeedItemCard
import io.github.ginger.lithotutorial.lithography.data.Artist
import io.github.ginger.lithotutorial.lithography.data.Decade

@GroupSectionSpec
object DecadeSectionSpec {
  @OnCreateChildren
  fun onCreateChildren(c: SectionContext, @Prop decade: Decade) =
      Children.create()
          .child(
              SingleComponentSection.create(c)
                  .component(DecadeSeperator.create(c).decade(decade))
                  .sticky(true))
          .child(
              DataDiffSection.create<Artist>(c)
                  .data(decade.artists)
                  .renderEventHandler(DecadeSection.render(c))
                  .onCheckIsSameItemEventHandler(DecadeSection.isSameItem(c)))
          .build()

  @OnEvent(RenderEvent::class)
  fun render(c: SectionContext, @FromEvent model: Artist): RenderInfo =
      ComponentRenderInfo.create()
          .component(FeedItemCard.create(c).artist(model).build())
          .build()

  @OnEvent(OnCheckIsSameItemEvent::class)
  fun isSameItem(
      c: SectionContext,
      @FromEvent previousItem: Artist,
      @FromEvent nextItem: Artist): Boolean = previousItem.name == nextItem.name

}