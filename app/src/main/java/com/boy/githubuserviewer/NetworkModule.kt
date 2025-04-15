package com.boy.githubuserviewer

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideGitHubService(): GitHubService {
        val client = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder()
                    .addHeader("Authorization", "Bearer YOUR-GITHUB-ACCESS-TOKEN")
                    .build()
                it.proceed(request)
            }.build()

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(GitHubService::class.java)
    }
}