package com.example.directoryapp.contacts.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.directoryapp.BuildConfig
import com.example.directoryapp.common.NetworkInterceptor
import com.example.directoryapp.contacts.data.remote.ContactsApi
import com.example.directoryapp.contacts.data.repository.ContactsRepository
import com.example.directoryapp.contacts.data.repository.ContactsRepositoryImpl
import com.example.directoryapp.contacts.domain.use_case.GetContactDetails
import com.example.directoryapp.contacts.domain.use_case.GetContacts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ContactsModule {

    @Singleton
    @Provides
    fun provideBaseURL(): String {
        return BuildConfig.BASE_URL
    }

    @Singleton
    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(): NetworkInterceptor {
        return NetworkInterceptor()
    }

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager


    @Singleton
    @Provides
    fun provideOkHttp(
        interceptor: HttpLoggingInterceptor,
        networkInterceptor: NetworkInterceptor
    ): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.callTimeout(60, TimeUnit.SECONDS)
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        okHttpClient.addInterceptor(networkInterceptor)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseURL: String, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideContactsApi(retrofit: Retrofit): ContactsApi {
        return retrofit.create(ContactsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContactsRepository(contactsApi: ContactsApi): ContactsRepository {
        return ContactsRepositoryImpl(contactsApi)
    }

    @Provides
    @Singleton
    fun provideGetContactsUseCase(contactsRepository: ContactsRepository): GetContacts {
        return GetContacts(contactsRepository)
    }

    @Provides
    @Singleton
    fun provideGetContactDetails(): GetContactDetails {
        return GetContactDetails()
    }

}










