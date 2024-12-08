package io.github.like475.telechat.telegram

import com.github.kotlintelegrambot.Bot
import com.github.kotlintelegrambot.bot
import com.github.kotlintelegrambot.dispatch
import com.github.kotlintelegrambot.dispatcher.text
import com.github.kotlintelegrambot.entities.ChatId
import io.github.like475.telechat.processor.Processor

object TelegramBot {
    private lateinit var bot: Bot
    private lateinit var chatId: ChatId

    fun init(token: String, id: Long) {
        this.bot = bot {
            this.token = token
            dispatch {
                text {
                    if (message.from == null) return@text
                    Processor.processTelegramMessage(message.from!!, text)
                }
            }
        }
        this.chatId = ChatId.fromId(id)
        this.bot.startPolling()
    }

    fun sendMessage(message: String) { bot.sendMessage(chatId, message) }
}