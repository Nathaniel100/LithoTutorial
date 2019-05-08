package io.github.ginger.lithotutorial.lithography.components

import com.facebook.litho.ClickEvent
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.Row
import com.facebook.litho.StateValue
import com.facebook.litho.annotations.LayoutSpec
import com.facebook.litho.annotations.OnCreateLayout
import com.facebook.litho.annotations.OnEvent
import com.facebook.litho.annotations.OnUpdateState
import com.facebook.litho.annotations.State

@LayoutSpec
object FavouriteButtonSpec {
  @OnCreateLayout
  fun onCreateLayout(
      c: ComponentContext,
      @State favourited: Boolean): Component =
      Row.create(c)
          .backgroundRes(
              if (favourited) android.R.drawable.star_on else android.R.drawable.star_off)
          .widthDip(32f)
          .heightDip(32f)
          .clickHandler(FavouriteButton.onClick(c))
          .build()

  @OnUpdateState
  fun toggleFavourited(favourited: StateValue<Boolean>) = favourited.set(!favourited.get())

  @OnEvent(ClickEvent::class)
  fun onClick(c: ComponentContext): Unit = FavouriteButton.toggleFavourited(c)
}