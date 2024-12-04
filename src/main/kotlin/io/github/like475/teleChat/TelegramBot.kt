package io.github.like475.teleChat

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId

object TelegramBot {
    lateinit var bot: Bot
    private lateinit var chatId: ChatId

    private var messageCallback: ((String) -> Unit)? = null

    fun setMessageCallback(callback: (String) -> Unit) {
        messageCallback = callback
    }

    fun init(token: String, id: Long) {
        bot = bot {
            this.token = token

            dispatch {
                text {
                    println(text)
                    messageCallback?.invoke("<TG ${message.from?.username}> $text")
                }
            }
        }
        chatId = ChatId.fromId(id)
        bot.startPolling()
    }

    fun send(message: String) {
        if (::bot.isInitialized) {
            bot.sendMessage(chatId, message)
        }
    }
}