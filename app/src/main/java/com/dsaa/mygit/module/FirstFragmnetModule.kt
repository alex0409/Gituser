package com.dsaa.mygit.module

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.dsaa.mygit.adapter.UserListAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class FirstFragmnetModule {
    @Provides
    fun provideUserListAdapter(): UserListAdapter {
        return UserListAdapter()
    }
}