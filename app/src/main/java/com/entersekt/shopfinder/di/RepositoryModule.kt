package com.entersekt.shopfinder.di

import com.entersekt.shops.SFService
import org.koin.dsl.module

val repositoryModule = module {
    single { SFService(get()) }
}