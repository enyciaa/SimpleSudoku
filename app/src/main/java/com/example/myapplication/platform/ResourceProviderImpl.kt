package com.example.myapplication.platform

import android.app.Application
import androidx.annotation.StringRes
import com.example.myapplication.domain.ResourceProvider
import dagger.Reusable
import javax.inject.Inject

@Reusable
class ResourceProviderImpl @Inject constructor(
        private val application: Application
) : ResourceProvider {

    private val resources by lazy { application.resources }

    override fun getString(@StringRes stringRes: Int): String {
        return resources.getString(stringRes)
    }
}