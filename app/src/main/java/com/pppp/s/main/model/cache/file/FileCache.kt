package com.pppp.s.main.model.cache.file

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pppp.s.main.model.cache.Cache
import com.pppp.s.main.model.pokos.Movie
import org.json.JSONException
import java.io.*

class FileCache(private val context: Context) : Cache() {
    private val cacheDir: File
        get() = context.externalCacheDir ?: context.cacheDir

    private val file: File
        get() = File(cacheDir, FILE_NAME)

    @Synchronized
    override fun getMovies(): List<Movie>? {
        val stringBuilder = StringBuilder()
        var bufferedReader: BufferedReader? = null
        try {
            bufferedReader = BufferedReader(FileReader(file))
            var line = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line)
                stringBuilder.append('\n')
                line = bufferedReader.readLine()
            }
            val type = object : TypeToken<List<Movie>>() {}.getType()
            return Gson().fromJson<List<Movie>>(stringBuilder.toString(), type)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        } catch (jsonException: JSONException) {
            jsonException.printStackTrace()
        } finally {
            close(bufferedReader)
        }
        return emptyList()
    }

    @Synchronized
    override fun putMovies(movies: List<Movie>, timestamp: Long) {
        val withTimestamp = movies.map { it.copy(timestamp = timestamp) }
        val string = Gson().toJson(withTimestamp)
        var outputStreamWriter: OutputStreamWriter? = null
        try {
            outputStreamWriter = OutputStreamWriter(FileOutputStream(file))
            outputStreamWriter.write(string)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            close(outputStreamWriter)
        }
    }

    fun close(c: Closeable?) {
        if (c == null) return
        try {
            c.close()
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }
    }

    companion object {
        private const val FILE_NAME = "response"
    }
}

