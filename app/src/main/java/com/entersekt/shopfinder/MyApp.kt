package com.entersekt.shopfinder

import android.app.Application
import com.entersekt.shopfinder.di.ModuleLoadHelper
import com.entersekt.shopfinder.di.persistenceModule
import com.entersekt.shopfinder.di.repositoryModule
import com.entersekt.shopfinder.di.viewModelModule
import com.entersekt.shops.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    persistenceModule,
                    networkingModule
                ) + ModuleLoadHelper.getBuildSpecialModuleList()
            )
        }
    }
}