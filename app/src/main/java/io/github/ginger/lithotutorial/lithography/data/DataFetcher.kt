package io.github.ginger.lithotutorial.lithography.data

import android.arch.lifecycle.MutableLiveData
import android.os.AsyncTask


class DataFetcher(val model: MutableLiveData<Model>) : Fetcher {
  private var fetching = -1
  override fun invoke(lastFetchedDecade: Int) {
    if (fetching == lastFetchedDecade + 1) {
      return
    }
    fetching = lastFetchedDecade + 1
    model.value = model.value?.let {
      Model(it.decades, true)
    }
    FetchTask(model, lastFetchedDecade).execute()
  }

  class FetchTask(val model: MutableLiveData<Model>, private val lastFetchedDecade: Int) :
    AsyncTask<Void, Void, List<Decade>>() {
    override fun doInBackground(vararg params: Void?): List<Decade> {
      Thread.sleep(2000)
      return DataCreator.createPageOfData(lastFetchedDecade + 1)
    }

    override fun onPostExecute(result: List<Decade>?) {
      result?.let {
        val decades = model.value?.decades ?: emptyList()
        model.value = Model(decades + it, false)
      }
    }
  }

}