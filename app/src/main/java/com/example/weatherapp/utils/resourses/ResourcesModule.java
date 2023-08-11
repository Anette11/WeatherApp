package com.example.weatherapp.utils.resourses;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
abstract class ResourcesModule {

    @Binds
    abstract ResourcesProvider bindResourcesProvider(ResourcesProviderImpl resourcesProviderImpl);
}