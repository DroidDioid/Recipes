package ru.tim.recipes.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tim.recipes.data.datasource.api.RecipesApiService
import ru.tim.recipes.data.datasource.api.RecipesNetworkDataSource
import ru.tim.recipes.data.datasource.local.RecipeDatabase
import ru.tim.recipes.data.datasource.local.RecipeDatabaseDao
import ru.tim.recipes.data.datasource.local.RecipesLocalDataSource
import ru.tim.recipes.data.repository.RecipesRepositoryImpl
import ru.tim.recipes.domain.RecipesRepository
import javax.inject.Singleton

private const val DATABASE_NAME = "recipe-database"
private const val URL_BASE = "https://hf-android-app.s3-eu-west-1.amazonaws.com/android-test/"

/**
 * Модуль внедрения зависимостей, описывающий способ создания объектов,
 * необходимых для работы с данными.
 * */
@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    /** Предоставляет экземпляр [RecipeDatabase] на основании [контекста][context]. */
    @Provides
    fun provideRecipeDatabase(@ApplicationContext context: Context): RecipeDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            RecipeDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    /** Предоставляет экземпляр [RecipeDatabaseDao] на основании [database]. */
    @Provides
    fun provideRecipeDatabaseDao(database: RecipeDatabase): RecipeDatabaseDao {
        return database.recipeDatabaseDao
    }

    /** Предоставляет экземпляр [Retrofit]. */
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /** Предоставляет экземпляр [RecipesApiService] на основании [retrofit]. */
    @Provides
    fun provideRecipesApiService(retrofit: Retrofit): RecipesApiService {
        return retrofit.create(RecipesApiService::class.java)
    }

    /** Предоставляет экземпляр [RecipesRepository]
     * на основании [recipesNetworkDataSource] и [recipesLocalDataSource]. */
    @Provides
    fun provideRecipesRepository(
        recipesNetworkDataSource: RecipesNetworkDataSource,
        recipesLocalDataSource: RecipesLocalDataSource
    ): RecipesRepository {

        return RecipesRepositoryImpl(recipesNetworkDataSource, recipesLocalDataSource)
    }
}