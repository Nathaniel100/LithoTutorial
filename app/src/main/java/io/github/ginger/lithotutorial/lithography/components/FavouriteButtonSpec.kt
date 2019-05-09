package io.github.ginger.lithotutorial.lithography.components

import com.facebook.litho.*
import com.facebook.litho.annotations.*

@LayoutSpec
object FavouriteButtonSpec {
  @OnCreateLayout
  fun onCreateLayout(
    c: ComponentContext,
    @State favourited: Boolean
  ): Component =
    Row.create(c)
      .backgroundRes(
        if (favourited) android.R.drawable.star_on else android.R.drawable.star_off
      )
      .widthDip(32f)
      .heightDip(32f)
      .clickHandler(FavouriteButton.onClick(c))
      .build()

  @OnUpdateState
  fun toggleFavourited(favourited: StateValue<Boolean>) =
    favourited.set(!(favourited.get() ?: false))

  @OnEvent(ClickEvent::class)
  fun onClick(c: ComponentContext): Unit = FavouriteButton.toggleFavourited(c)
}