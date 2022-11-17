package am.solidy.data.di

import am.solidy.data.BuildConfig
import am.solidy.data.network.api.PostApi
import am.solidy.data.network.api.UserApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val TIMEOUT_CONNECTION_SECONDS = 20L
    private const val TIMEOUT_READ_SECONDS = 20L
    private const val TIMEOUT_WRITE_SECONDS = 20L

    @Provides
    @Singleton
    fun provideRetrofitClient(
        client: OkHttpClient,
        jsonConfigs: Json
    ): Retrofit = createRetrofitClient(client, jsonConfigs)


    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .followRedirects(true)
        .followSslRedirects(true)
        .connectTimeout(TIMEOUT_CONNECTION_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_READ_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_WRITE_SECONDS, TimeUnit.SECONDS)
        .addInterceptor(loggingInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJsonConfigurations(): Json = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
        encodeDefaults = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private fun createRetrofitClient(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {

        val converterFactory = json.asConverterFactory("application/json".toMediaType())

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    private inline fun <reified T> createApiService(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit) = createApiService<UserApi>(retrofit)

    @Provides
    @Singleton
    fun providePostApi(retrofit: Retrofit) = createApiService<PostApi>(retrofit)

}