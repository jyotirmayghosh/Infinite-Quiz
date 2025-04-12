package com.jyotirmay.infinitequiz

import com.jyotirmay.infinitequiz.data.repository.RepositoryImpl
import com.jyotirmay.infinitequiz.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(quizRepositoryImpl: RepositoryImpl): Repository
}