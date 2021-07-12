package com.entersekt.shopfinder.di

import com.entersekt.shopfinder.ui.dashboard.DashboardViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { DashboardViewModel(androidApplication(), get()) }
}
