package io.github.ginger.lithotutorial.lithography.components

import android.graphics.Color
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.widget.Progress
import com.facebook.yoga.YogaJustify.CENTER

@LayoutSpec
object LoadingComponentSpec {
  @OnCreateLayout
  fun onCreateLayout(c: ComponentContext): Component =
    Row.create(c)
      .justifyContent(CENTER)
      .child(
        Progress.create(c)
          .color(Color.DKGRAY)
          .widthDip(50f)
      )
      .build()
}