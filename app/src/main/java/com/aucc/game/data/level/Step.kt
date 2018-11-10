package com.aucc.game.data.level

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import com.aucc.game.data.level.Level

@Entity(tableName = "steps")
data class Step(@ColumnInfo(name = "desc") val desc: String,
                @ForeignKey(entity = Level::class, parentColumns = ["id"], childColumns = ["levelId"]) val levelId: Int) {

    @PrimaryKey(autoGenerate = true) var id: Int? = null
}

