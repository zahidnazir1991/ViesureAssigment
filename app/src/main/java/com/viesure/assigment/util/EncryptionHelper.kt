package com.viesure.assigment.util

import android.content.Context
import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

object EncryptionHelper {
    private const val ALGORITHM = "AES"
    private const val TRANSFORMATION = "AES"
    private const val KEY_PREF_NAME = "secret_prefs"
    private const val KEY_PREF_KEY = "secret_key"

    fun getOrCreateKey(context: Context): SecretKey {
        val sharedPreferences = context.getSharedPreferences(KEY_PREF_NAME, Context.MODE_PRIVATE)
        val keyString = sharedPreferences.getString(KEY_PREF_KEY, null)
        return if (keyString != null) {
            val decodedKey = Base64.decode(keyString, Base64.DEFAULT)
            SecretKeySpec(decodedKey, 0, decodedKey.size, ALGORITHM)
        } else {
            val secretKey = generateKey()
            saveKey(secretKey, context)
            secretKey
        }
    }

    private fun generateKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance(ALGORITHM)
        keyGen.init(256)
        return keyGen.generateKey()
    }

    private fun saveKey(secretKey: SecretKey, context: Context) {
        val keyString = Base64.encodeToString(secretKey.encoded, Base64.DEFAULT)
        val sharedPreferences = context.getSharedPreferences(KEY_PREF_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(KEY_PREF_KEY, keyString).apply()
    }

    fun encrypt(data: String?, secretKey: SecretKey): String {
        if (data.isNullOrEmpty()) {
            return ""
        }
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encryptedValue = cipher.doFinal(data.toByteArray())
        return Base64.encodeToString(encryptedValue, Base64.DEFAULT)
    }

    fun decrypt(data: String?, secretKey: SecretKey): String {
        if (data.isNullOrBlank()) {
            return ""
        }
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKey)
        val decodedValue = Base64.decode(data, Base64.DEFAULT)
        val decryptedValue = cipher.doFinal(decodedValue)
        return String(decryptedValue)
    }
}