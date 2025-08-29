package com.privacy.message.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val id: String,
    val phoneNumber: String,
    val uuid: String,
    val name: String? = null,
    val profileKey: ByteArray? = null,
    val avatarUrl: String? = null,
    val isVerified: Boolean = false,
    val lastSeen: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
) : Parcelable {
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (phoneNumber != other.phoneNumber) return false
        if (uuid != other.uuid) return false
        if (name != other.name) return false
        if (profileKey != null) {
            if (other.profileKey == null) return false
            if (!profileKey.contentEquals(other.profileKey)) return false
        } else if (other.profileKey != null) return false
        if (avatarUrl != other.avatarUrl) return false
        if (isVerified != other.isVerified) return false
        if (lastSeen != other.lastSeen) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + phoneNumber.hashCode()
        result = 31 * result + uuid.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (profileKey?.contentHashCode() ?: 0)
        result = 31 * result + (avatarUrl?.hashCode() ?: 0)
        result = 31 * result + isVerified.hashCode()
        result = 31 * result + lastSeen.hashCode()
        result = 31 * result + createdAt.hashCode()
        return result
    }
}