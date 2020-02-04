package com.example.oblig1

import androidx.test.espresso.IdlingResource

class DownloadAsyncIdlingResource : IdlingResource{
    private var resourceCallback: IdlingResource.ResourceCallback? = null


    override fun getName(): String {
        return DownloadAsyncIdlingResource::class.java.name
    }

    override fun isIdleNow(): Boolean {
        return if (MainActivity.databaseDownloaded) {
            resourceCallback?.onTransitionToIdle()
            true
        } else {
            false
        }
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }
}
