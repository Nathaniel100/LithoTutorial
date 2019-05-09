package io.github.ginger.lithotutorial.lithography.components

import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaAlign.CENTER
import com.facebook.yoga.YogaEdge.ALL
import com.facebook.yoga.YogaEdge.HORIZONTAL
import io.github.ginger.lithotutorial.lithography.data.Decade

@LayoutSpec
object DecadeSeperatorSpec {

  @OnCreateLayout
  fun onCreateLayout(
    c: ComponentContext,
    @Prop decade: Decade
  ): Component =
    Row.create(c)
      .alignItems(CENTER)
      .paddingDip(ALL, 16f)
      .child(
        Row.create(c)
          .heightPx(1)
          .backgroundColor(0xFFAAAAAA.toInt())
          .flex(1f)
      )
      .child(
        Text.create(c)
          .text(decade.year.toString())
          .textSizeDip(14f)
          .textColor(0xFFAAAAAA.toInt())
          .marginDip(HORIZONTAL, 10f)
          .flex(0f)
      )
      .child(
        Row.create(c)
          .heightPx(1)
          .backgroundColor(0xFFAAAAAA.toInt())
          .flex(1f)
      )
      .backgroundColor(0xFFFAFAFA.toInt())
      .build()

}