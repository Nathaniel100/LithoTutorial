package io.github.ginger.lithotutorial

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.NavUtils
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.ginger.lithotutorial.demo.DataModels
import io.github.ginger.lithotutorial.demo.DemoListActivity
import io.github.ginger.lithotutorial.demo.DemoListActivity.Companion.INDICES
import java.util.*


@SuppressLint("Registered")
open class NavigatableDemoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    intent.getIntArrayExtra(INDICES)
      .takeIf { it != null }
      ?.let {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitleFromIndex(it)
      }
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return if (item.itemId == android.R.id.home) {
      NavUtils.navigateUpFromSameTask(this)
      true
    } else {
      super.onOptionsItemSelected(item)
    }
  }

  override fun getParentActivityIntent(): Intent? {
    val indices = intent.getIntArrayExtra(INDICES) ?: return null
    return Intent(this, DemoListActivity::class.java)
      .also {
        if (indices.size > 1) {
          it.putExtra(INDICES, Arrays.copyOf(indices, indices.size - 1))
        }
      }
  }

  private fun setTitleFromIndex(indices: IntArray) {
    var dataModels = DataModels.DATA_MODELS
    for (i in 0 until indices.size - 1) {
      dataModels = dataModels[indices[i]].datamodels ?: dataModels
    }
    dataModels[indices[indices.size - 1]].also { title = it.name }
  }
}