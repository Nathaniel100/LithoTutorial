package io.github.ginger.lithotutorial.lithography.components

import android.graphics.Color.GRAY
import android.graphics.Typeface.ITALIC
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.annotations.ResType
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge.ALL

@LayoutSpec
object FooterComponentSpec {

  @OnCreateLayout
  fun onCreateLayout(
    c: ComponentContext,
    @Prop(resType = ResType.STRING) text: String
  ): Component =
    Column.create(c)
      .paddingDip(ALL, 8f)
      .child(
        Text.create(c)
          .text(text)
          .textSizeDip(14f)
          .textColor(GRAY)
          .textStyle(ITALIC)
      )
      .build()
}