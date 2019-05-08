package io.github.ginger.lithotutorial.demo

import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.annotations.*
import com.facebook.litho.sections.SectionContext
import com.facebook.litho.sections.common.DataDiffSection
import com.facebook.litho.sections.common.OnCheckIsSameContentEvent
import com.facebook.litho.sections.common.OnCheckIsSameItemEvent
import com.facebook.litho.sections.common.RenderEvent
import com.facebook.litho.sections.widget.RecyclerCollectionComponent
import com.facebook.litho.widget.ComponentRenderInfo
import com.facebook.litho.widget.RenderInfo
import java.util.*

@LayoutSpec
object DemoListComponentSpec {
  private val MAIN_SCREEN = "main_screen"

  @OnCreateLayout
  fun onCreateLayout(
    c: ComponentContext,
    @Prop dataModels: List<DemoListDataModel>
  ): Component =
    RecyclerCollectionComponent.create(c)
      .section(
        DataDiffSection.create<DemoListDataModel>(SectionContext(c)).data(dataModels)
          .renderEventHandler(DemoListComponent.onRender(c))
          .onCheckIsSameItemEventHandler(DemoListComponent.isSameItem(c))
          .onCheckIsSameContentEventHandler(DemoListComponent.isSameContent(c))
          .build()
      )
      .disablePTR(true)
      .testKey(MAIN_SCREEN)
      .build()

  @OnEvent(RenderEvent::class)
  fun onRender(
    c: ComponentContext,
    @Prop parentIndices: IntArray?,
    @FromEvent model: DemoListDataModel,
    @FromEvent index: Int
  ): RenderInfo =
    ComponentRenderInfo.create()
      .component(
        DemoListItemComponent.create(c).model(model).currentIndices(
          getUpdatedIndices(
            parentIndices,
            index
          )
        ).build()
      )
      .build()

  private fun getUpdatedIndices(parentIndices: IntArray?, currentIndex: Int): IntArray =
    if (parentIndices == null) {
      intArrayOf(currentIndex)
    } else {
      val updatedIndices = Arrays.copyOf(parentIndices, parentIndices.size + 1)
      updatedIndices[parentIndices.size] = currentIndex
      updatedIndices
    }

  @OnEvent(OnCheckIsSameItemEvent::class)
  fun isSameItem(
    c: ComponentContext,
    @FromEvent previousItem: DemoListDataModel,
    @FromEvent nextItem: DemoListDataModel
  ) = previousItem == nextItem

  @OnEvent(OnCheckIsSameContentEvent::class)
  fun isSameContent(
    c: ComponentContext,
    @FromEvent previousItem: DemoListDataModel?,
    @FromEvent nextItem: DemoListDataModel?
  ) = if (previousItem == null) nextItem == null else nextItem?.name == previousItem.name
}