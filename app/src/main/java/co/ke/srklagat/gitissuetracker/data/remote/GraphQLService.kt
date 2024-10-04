package co.ke.srklagat.gitissuetracker.data.remote

import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

object GraphQLService {
    private const val BASE_URL = "https://api.github.com/graphql"
    private const val TOKEN = "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"

    private val client = OkHttpClient()

    fun fetchIssues(query: String): String? {
        val body = buildJsonObject {
            put("query", query)
        }.toString()

        val request = Request.Builder()
            .url(BASE_URL)
            .header("Authorization", "Bearer $TOKEN")
            .post(body.toRequestBody())
            .build()

        return try {
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")
                response.body?.string()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}
