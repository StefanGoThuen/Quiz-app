package com.example.oblig1

import androidx.test.espresso.IdlingResource

class SaveImagIdlingResource : IdlingResource {
    private var resourceCallback: IdlingResource.ResourceCallback? = null


    override fun getName(): String {
        return SaveImagIdlingResource::class.java.name
    }

    override fun isIdleNow(): Boolean {
        return if (ImageHandler.justForTestDontDoThisInRealLifeThanks) {
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