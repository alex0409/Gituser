package com.dsaa.mygit.module

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.dsaa.mygit.adapter.UserListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(FragmentComponent::class)
class FirstFragmnetModule {
    @Provides
    fun provideUserListAdapter(): UserListAdapter {
        return UserListAdapter()
    }

    @Provides
    fun providerLayoutManager(@ActivityContext context:Context):LinearLayoutManager{
        return LinearLayoutManager(context)
    }

}