package com.entersekt.shopfinder.di

import com.entersekt.shopfinder.persistance.room.SFDb
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {
    single { SFDb.getInstance(androidApplication())}
}