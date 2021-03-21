package com.example.erc_project

import android.app.Application
import com.example.erc_project.di.apiModule
import com.example.erc_project.mdoel.CollectionRepository
import com.example.erc_project.mdoel.DefaultCollectionRepository
import com.example.erc_project.view.main.CollectionViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

class ERCApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val allModules = listOf(viewModelModule, apiModule, modelModule)

        startKoin {
            androidLogger()
            androidContext(this@ERCApp)
            modules(allModules)
        }
    }
}

val viewModelModule = module {
    viewModel { CollectionViewModel() }
}

val modelModule = module {
    single { DefaultCollectionRepository(get()) as CollectionRepository}
}