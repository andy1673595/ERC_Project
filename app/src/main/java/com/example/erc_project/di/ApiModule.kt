package com.example.erc_project.di

import com.example.erc_project.Constant
import com.example.erc_project.mdoel.CollectionApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideCollectionApiService(get()) }
}

private fun provideCollectionApiService(okHttpClient: OkHttpClient): CollectionApiService {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(Constant.END_POINT)
        .build()
        .create(CollectionApiService::class.java)
}


private fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()
}

private fun provideInterceptor() : Interceptor {
    return Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
            .addHeader("Accept-Encoding", "identity")
            .build()
        chain.proceed(request)
    }
}
