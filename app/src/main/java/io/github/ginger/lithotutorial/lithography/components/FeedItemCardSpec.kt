package io.github.ginger.lithotutorial.lithography.components

import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Card
import com.facebook.yoga.YogaEdge.HORIZONTAL
import com.facebook.yoga.YogaEdge.VERTICAL
import io.github.ginger.lithotutorial.lithography.data.Artist

@LayoutSpec
object FeedItemCardSpec {
  @OnCreateLayout
  fun onCreateLayout(c: ComponentContext, @Prop artist: Artist): Component =
      Column.create(c)
          .paddingDip(VERTICAL, 8f)
          .paddingDip(HORIZONTAL, 16f)
          .child(
              Card.create(c)
                  .content(
                      FeedItemComponent.create(c)
                          .artist(artist)
                          .build()
                  )
                  .cornerRadiusDip(4f))
          .build()

}