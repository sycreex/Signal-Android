package com.privacy.message.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
@Entity(tableName = "conversations")
data class Conversation(
    @PrimaryKey
    val id: String,
    val type: ConversationType = ConversationType.INDIVIDUAL,
    val participants: List<String> = emptyList(), // User IDs
    val lastMessageId: String? = null,
    val lastMessageContent: String? = null,
    val lastMessageTimestamp: Long = 0L,
    val unreadCount: Int = 0,
    val isArchived: Boolean = false,
    val isPinned: Boolean = false,
    val disappearingMessageDuration: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Parcelable {
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Conversation

        if (id != other.id) return false
        if (type != other.type) return false
        if (participants != other.participants) return false
        if (lastMessageId != other.lastMessageId) return false
        if (lastMessageContent != other.lastMessageContent) return false
        if (lastMessageTimestamp != other.lastMessageTimestamp) return false
        if (unreadCount != other.unreadCount) return false
        if (isArchived != other.isArchived) return false
        if (isPinned != other.isPinned) return false
        if (disappearingMessageDuration != other.disappearingMessageDuration) return false
        if (createdAt != other.createdAt) return false
        if (updatedAt != other.updatedAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + participants.hashCode()
        result = 31 * result + (lastMessageId?.hashCode() ?: 0)
        result = 31 * result + (lastMessageContent?.hashCode() ?: 0)
        result = 31 * result + lastMessageTimestamp.hashCode()
        result = 31 * result + unreadCount
        result = 31 * result + isArchived.hashCode()
        result = 31 * result + isPinned.hashCode()
        result = 31 * result + (disappearingMessageDuration?.hashCode() ?: 0)
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }
}

enum class ConversationType {
    INDIVIDUAL,
    GROUP
}