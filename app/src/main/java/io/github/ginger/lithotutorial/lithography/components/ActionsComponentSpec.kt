package io.github.ginger.lithotutorial.lithography.components

import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.yoga.YogaEdge.ALL
import com.facebook.yoga.YogaEdge.RIGHT
import com.facebook.yoga.YogaEdge.TOP
import com.facebook.yoga.YogaPositionType.ABSOLUTE

@LayoutSpec
object ActionsComponentSpec {
  @OnCreateLayout
  fun onCreateLayout(c: ComponentContext): Component =
      Row.create(c)
          .backgroundColor(0xDDFFFFFF.toInt())
          .positionType(ABSOLUTE)
          .positionDip(RIGHT, 4f)
          .positionDip(TOP, 4f)
          .paddingDip(ALL, 2f)
          .child(FavouriteButton.create(c))
          .build()
}