package com.privacy.message.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
@Entity(tableName = "messages")
data class Message(
    @PrimaryKey
    val id: String,
    val conversationId: String,
    val senderId: String,
    val content: String? = null,
    val encryptedContent: ByteArray? = null,
    val messageType: MessageType = MessageType.TEXT,
    val timestamp: Long = System.currentTimeMillis(),
    val isRead: Boolean = false,
    val isOutgoing: Boolean = false,
    val attachmentUrl: String? = null,
    val attachmentType: AttachmentType? = null,
    val replyToMessageId: String? = null,
    val disappearingMessageDuration: Long? = null,
    val expiresAt: Long? = null
) : Parcelable {
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Message

        if (id != other.id) return false
        if (conversationId != other.conversationId) return false
        if (senderId != other.senderId) return false
        if (content != other.content) return false
        if (encryptedContent != null) {
            if (other.encryptedContent == null) return false
            if (!encryptedContent.contentEquals(other.encryptedContent)) return false
        } else if (other.encryptedContent != null) return false
        if (messageType != other.messageType) return false
        if (timestamp != other.timestamp) return false
        if (isRead != other.isRead) return false
        if (isOutgoing != other.isOutgoing) return false
        if (attachmentUrl != other.attachmentUrl) return false
        if (attachmentType != other.attachmentType) return false
        if (replyToMessageId != other.replyToMessageId) return false
        if (disappearingMessageDuration != other.disappearingMessageDuration) return false
        if (expiresAt != other.expiresAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + conversationId.hashCode()
        result = 31 * result + senderId.hashCode()
        result = 31 * result + (content?.hashCode() ?: 0)
        result = 31 * result + (encryptedContent?.contentHashCode() ?: 0)
        result = 31 * result + messageType.hashCode()
        result = 31 * result + timestamp.hashCode()
        result = 31 * result + isRead.hashCode()
        result = 31 * result + isOutgoing.hashCode()
        result = 31 * result + (attachmentUrl?.hashCode() ?: 0)
        result = 31 * result + (attachmentType?.hashCode() ?: 0)
        result = 31 * result + (replyToMessageId?.hashCode() ?: 0)
        result = 31 * result + (disappearingMessageDuration?.hashCode() ?: 0)
        result = 31 * result + (expiresAt?.hashCode() ?: 0)
        return result
    }
}

enum class MessageType {
    TEXT,
    IMAGE,
    VIDEO,
    AUDIO,
    FILE,
    LOCATION,
    CONTACT,
    STICKER,
    SYSTEM
}

enum class AttachmentType {
    IMAGE,
    VIDEO,
    AUDIO,
    FILE,
    LOCATION
}