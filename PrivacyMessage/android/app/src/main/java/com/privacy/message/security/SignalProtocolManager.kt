package com.privacy.message.security

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.signal.libsignal.protocol.*
import org.signal.libsignal.protocol.state.PreKeyBundle
import org.signal.libsignal.protocol.state.SignalProtocolStore
import org.signal.libsignal.protocol.util.Medium
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignalProtocolManager @Inject constructor(
    @ApplicationContext private val context: Context,
    private val signalStore: SignalProtocolStore
) {
    
    private var sessionStore: SessionStore? = null
    private var preKeyStore: PreKeyStore? = null
    private var signedPreKeyStore: SignedPreKeyStore? = null
    private var identityKeyStore: IdentityKeyStore? = null
    
    suspend fun initialize(): Boolean = withContext(Dispatchers.IO) {
        try {
            // Initialize stores
            sessionStore = signalStore.sessionStore
            preKeyStore = signalStore.preKeyStore
            signedPreKeyStore = signalStore.signedPreKeyStore
            identityKeyStore = signalStore.identityKeyStore
            
            // Generate identity key if not exists
            if (identityKeyStore?.getIdentityKeyPair() == null) {
                generateIdentityKeyPair()
            }
            
            // Generate pre-keys
            generatePreKeys()
            
            Timber.d("Signal protocol manager initialized successfully")
            true
        } catch (e: Exception) {
            Timber.e(e, "Failed to initialize Signal protocol manager")
            false
        }
    }
    
    suspend fun createSession(
        recipientId: String,
        preKeyBundle: PreKeyBundle
    ): Boolean = withContext(Dispatchers.IO) {
        try {
            val sessionBuilder = SessionBuilder(sessionStore, preKeyStore, signedPreKeyStore, identityKeyStore, recipientId, 1)
            sessionBuilder.process(preKeyBundle)
            Timber.d("Session created for recipient: $recipientId")
            true
        } catch (e: Exception) {
            Timber.e(e, "Failed to create session for recipient: $recipientId")
            false
        }
    }
    
    suspend fun encryptMessage(
        recipientId: String,
        message: String
    ): ByteArray? = withContext(Dispatchers.IO) {
        try {
            val sessionCipher = SessionCipher(sessionStore, preKeyStore, signedPreKeyStore, identityKeyStore, recipientId, 1)
            val ciphertextMessage = sessionCipher.encrypt(message.toByteArray())
            
            when (ciphertextMessage) {
                is PreKeySignalMessage -> ciphertextMessage.serialize()
                is SignalMessage -> ciphertextMessage.serialize()
                else -> null
            }
        } catch (e: Exception) {
            Timber.e(e, "Failed to encrypt message for recipient: $recipientId")
            null
        }
    }
    
    suspend fun decryptMessage(
        senderId: String,
        encryptedMessage: ByteArray
    ): String? = withContext(Dispatchers.IO) {
        try {
            val sessionCipher = SessionCipher(sessionStore, preKeyStore, signedPreKeyStore, identityKeyStore, senderId, 1)
            
            val message = when {
                PreKeySignalMessage.isPreKeySignalMessage(encryptedMessage) -> {
                    val preKeySignalMessage = PreKeySignalMessage(encryptedMessage)
                    sessionCipher.decrypt(preKeySignalMessage)
                }
                SignalMessage.isLegacy(encryptedMessage) -> {
                    val signalMessage = SignalMessage(encryptedMessage)
                    sessionCipher.decrypt(signalMessage)
                }
                else -> {
                    Timber.w("Unknown message format")
                    return@withContext null
                }
            }
            
            String(message)
        } catch (e: Exception) {
            Timber.e(e, "Failed to decrypt message from sender: $senderId")
            null
        }
    }
    
    private fun generateIdentityKeyPair() {
        val identityKeyPair = IdentityKeyPair.generate()
        identityKeyStore?.saveIdentityKeyPair(identityKeyPair)
        identityKeyStore?.saveLocalRegistrationId(Medium.MAX_VALUE)
        Timber.d("Identity key pair generated")
    }
    
    private fun generatePreKeys() {
        val preKeyCount = 100
        val preKeyList = mutableListOf<PreKeyRecord>()
        
        for (i in 0 until preKeyCount) {
            val preKey = PreKeyRecord.generate()
            preKeyList.add(preKey)
            preKeyStore?.storePreKey(preKey.id, preKey)
        }
        
        val signedPreKey = SignedPreKeyRecord.generate(identityKeyStore?.getIdentityKeyPair()!!)
        signedPreKeyStore?.storeSignedPreKey(signedPreKey.id, signedPreKey)
        
        Timber.d("Generated $preKeyCount pre-keys and 1 signed pre-key")
    }
    
    fun getIdentityKeyPair(): IdentityKeyPair? {
        return identityKeyStore?.getIdentityKeyPair()
    }
    
    fun getRegistrationId(): Int? {
        return identityKeyStore?.getLocalRegistrationId()
    }
    
    fun getPreKeyBundle(): PreKeyBundle? {
        val identityKeyPair = identityKeyStore?.getIdentityKeyPair() ?: return null
        val registrationId = identityKeyStore?.getLocalRegistrationId() ?: return null
        
        // Get a random pre-key
        val preKeyId = (1..100).random()
        val preKey = preKeyStore?.loadPreKey(preKeyId) ?: return null
        
        // Get signed pre-key
        val signedPreKeyId = 1
        val signedPreKey = signedPreKeyStore?.loadSignedPreKey(signedPreKeyId) ?: return null
        
        return PreKeyBundle(
            registrationId,
            1, // deviceId
            preKeyId,
            preKey.keyPair.publicKey,
            signedPreKeyId,
            signedPreKey.keyPair.publicKey,
            signedPreKey.signature,
            identityKeyPair.publicKey
        )
    }
}