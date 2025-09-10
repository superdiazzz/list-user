package id.testing.list_user.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.testing.list_user.repository.UserRepository
import id.testing.list_user.repository.UserRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun provideUserRepository(impl: UserRepositoryImpl) : UserRepository
}