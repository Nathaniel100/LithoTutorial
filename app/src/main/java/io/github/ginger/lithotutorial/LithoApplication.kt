package io.github.ginger.lithotutorial

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.soloader.SoLoader


class LithoApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    Fresco.initialize(this)
    SoLoader.init(this, false)
  }
}