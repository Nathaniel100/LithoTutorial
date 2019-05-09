package io.github.ginger.lithotutorial.demo

import android.content.Intent
import android.view.View
import com.facebook.litho.ClickEvent
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.*
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge
import io.github.ginger.lithotutorial.demo.DemoListActivity.Companion.INDICES

@LayoutSpec
object DemoListItemComponentSpec {

  @OnCreateLayout
  fun onCreateLayout(
    c: ComponentContext,
    @Prop model: DemoListDataModel
  ): Component =
    Column.create(c).paddingDip(YogaEdge.ALL, 16f)
      .child(Text.create(c).text(model.name).textSizeSp(18f).build())
      .clickHandler(DemoListItemComponent.onClick(c))
      .build()

  @OnEvent(ClickEvent::class)
  fun onClick(
    c: ComponentContext,
    @FromEvent view: View,
    @Prop model: DemoListDataModel,
    @Prop currentIndices: IntArray
  ) {
    val activityClass = if (model.datamodels == null) model.kClass else DemoListActivity::class.java
    val intent = Intent(c.androidContext, activityClass)
    intent.putExtra(INDICES, currentIndices)
    c.androidContext.startActivity(intent)
  }
}