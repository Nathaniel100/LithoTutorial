package io.github.ginger.lithotutorial.lithography.components

import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.Prop
import com.facebook.litho.annotations.PropDefault
import com.facebook.litho.fresco.FrescoImage

@LayoutSpec
object SingleImageComponentSpec {
  @PropDefault
  val imageAspectRatio = 1f

  @OnCreateLayout
  fun onCreateLayout(
      c: ComponentContext,
      @Prop image: String,
      @Prop(optional = true) imageAspectRatio: Float): Component =
      Fresco.newDraweeControllerBuilder()
          .setUri(image)
          .build().let {
            FrescoImage.create(c)
                .controller(it)
                .imageAspectRatio(imageAspectRatio)
                .build()
          }
}