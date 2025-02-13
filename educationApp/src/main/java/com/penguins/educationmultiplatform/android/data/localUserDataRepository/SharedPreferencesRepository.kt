package com.penguins.educationmultiplatform.android.data.localUserDataRepository

import android.content.Context
import androidx.preference.PreferenceManager
import com.penguins.educationmultiplatform.android.authScreen.data.UserTokens
import com.penguins.educationmultiplatform.android.domain.localUserDataRepository.LocalUserDataRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SharedPreferencesRepository(
    context: Context
): LocalUserDataRepository {

    private val repository = PreferenceManager.getDefaultSharedPreferences(context)

    override fun setSkippedAuthorization(value: Boolean) {
        repository
            .edit()
            .putBoolean(skippedAuthFlag, value)
            .apply()
    }

    override fun getSkippedAuthorization(): Boolean = repository.getBoolean(
        skippedAuthFlag,
        false
    )

    override fun getTokens(): UserTokens? = when (val tokensString = repository.getString(userTokensData, null)) {
        null -> null
        else -> { Json.decodeFromString<UserTokens>(tokensString) }
    }

    override fun setTokens(tokens: UserTokens) {
        val jsonString = Json.encodeToString<UserTokens>(tokens)
        repository
            .edit()
            .putString(userTokensData, jsonString)
            .apply()
    }

    override fun logoutUser() {
        repository
            .edit()
            .clear()
            .apply()
    }

    companion object {
        private const val skippedAuthFlag = "skipped_auth_property"
        private const val userTokensData = "user_tokens_property"
    }
}