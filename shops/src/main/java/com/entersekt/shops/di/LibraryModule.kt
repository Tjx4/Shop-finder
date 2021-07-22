package com.entersekt.shops.di

import com.entersekt.outlets.retrofit.API
import org.koin.dsl.module

val networkingModule = module {
    single { API.retrofit }
}
