package com.entersekt.shopfinder.di

import com.entersekt.outlets.retrofit.API
import com.entersekt.shopfinder.persistance.room.SFDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkingModule = module {
    single { API.retrofit }
}

val persistenceModule = module {
    single { SFDb.getInstance(androidApplication())}
}