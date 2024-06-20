package com.lukemartinrecords.encorehub

import android.app.Application
import com.lukemartinrecords.encorehub.data.SongRepository
import com.lukemartinrecords.encorehub.data.SongRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class EncoreHubApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { SongRoomDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { SongRepository(database.songDao(), database.ratingDao(),
        database.artistDao(), database.listDao(), database.listM2MDao() ) }
}