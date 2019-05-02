package com.example.androidtests.data.model

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

class Recipe private constructor(val id: String, val title: String, val description: String) {

    companion object {
        private const val ID_PREFIX = "id="
        private const val TITLE_PREFIX = "title="

        fun readFromStream(stream: InputStream): Recipe? {
            var id: String? = null
            var title: String? = null
            val descBuilder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(stream))

            try {
                var line: String? = reader.readLine()
                while (line != null) {
                    if (line.startsWith(ID_PREFIX)) {
                        id = line.substring(ID_PREFIX.length)
                        line = reader.readLine()
                        continue
                    }
                    if (line.startsWith(TITLE_PREFIX)) {
                        title = line.substring(TITLE_PREFIX.length)
                        line = reader.readLine()
                        continue
                    }
                    if (descBuilder.isNotEmpty()) {
                        descBuilder.append("\n")
                    }

                    descBuilder.append(line)
                    line = reader.readLine()
                }
            } catch (e: IOException) {
                return null
            }

            return if (id != null && title != null) Recipe(id, title, descBuilder.toString()) else null
        }
    }

}
