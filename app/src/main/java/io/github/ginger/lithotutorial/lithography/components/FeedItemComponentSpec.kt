package io.github.ginger.lithotutorial.lithography.components

import android.graphics.Typeface
import android.widget.LinearLayout
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.widget.ListRecyclerConfiguration
import com.facebook.litho.sections.widget.RecyclerCollectionComponent
import com.facebook.litho.sections.widget.SectionBinderTarget
import com.facebook.litho.widget.SnapUtil
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge.*
import com.facebook.yoga.YogaPositionType.ABSOLUTE
import io.github.ginger.lithotutorial.lithography.data.Artist
import io.github.ginger.lithotutorial.lithography.sections.ImagesSection

@LayoutSpec
object FeedItemComponentSpec {

  private val recyclerConfiguration: ListRecyclerConfiguration<SectionBinderTarget> =
    ListRecyclerConfiguration(LinearLayout.HORIZONTAL, false, SnapUtil.SNAP_TO_CENTER)

  @OnCreateLayout
  fun onCreateLayout(c: ComponentContext, @Prop artist: Artist): Component =
    Column.create(c)
      .child(
        Column.create(c)
          .child(imageBlock(artist, c))
          .child(
            Text.create(c)
              .text(artist.name)
              .textStyle(Typeface.BOLD)
              .textSizeDip(24f)
              .backgroundColor(0xDDFFFFFF.toInt())
              .positionType(ABSOLUTE)
              .positionDip(BOTTOM, 4f)
              .positionDip(LEFT, 4f)
              .positionDip(HORIZONTAL, 6f)
          )
          .child(ActionsComponent.create(c))
      )
      .child(FooterComponent.create(c).text(artist.biography))
      .build()

  private fun imageBlock(artist: Artist, c: ComponentContext): Component =
    when (artist.images.size) {
      1 -> singleImage(c, artist)
      else -> recycler(c, artist)
    }

  private fun recycler(c: ComponentContext, artist: Artist): Component =
    RecyclerCollectionComponent.create(c)
      .recyclerConfiguration(recyclerConfiguration)
      .section(
        ImagesSection.create(SectionContext(c))
          .images(artist.images)
          .build()
      )
      .aspectRatio(2f)
      .build()

  private fun singleImage(c: ComponentContext, artist: Artist): Component =
    SingleImageComponent.create(c)
      .image(artist.images[0])
      .imageAspectRatio(2f)
      .build()

}