package com.kokasai.flowerkt

import com.kokasai.flowerkt.route.*
import com.kokasai.flowerkt.session.*
import com.kokasai.the23rd.configure.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.sessions.*
import io.ktor.websocket.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.*
import java.time.*

interface FlowerKt {
    /**
     * データベースのURL
     */
    val databaseUrl: String

    /**
     * セッションを保存するデータベース
     */
    val sessionTable: SessionTable
        get() = SessionTable("session", Duration.ofDays(30))

    /**
     * データベースの初期化を行います
     */
    fun setupDatabase() {
        Database.connect(databaseUrl)
        transaction {
            create(sessionTable)
        }
    }

    /**
     * サーバーのポート番号です
     */
    val port: Int
        get() = 80

    /**
     * Ktor の機能をインストールします
     */
    fun Application.installKtorFeature() {
        install(Sessions) {
            configureAuthCookie()
        }

        install(Authentication) {
            configureFormAuth()
            configureSessionAuth()
        }

        install(ContentNegotiation) {
            configureGson()
        }

        install(WebSockets)
    }

    /**
     * ルートビルダーでルートの登録をします
     */
    val routeBuilder: RouteBuilder

    /**
     * サーバーのルーティングの設定をします
     */
    fun Routing.setupRouting() {
        routeBuilder.build(this)
    }

    /**
     * モジュールの設定をします
     */
    fun Application.setupServerModule() {
        installKtorFeature()
        routing {
            setupRouting()
        }
    }

    /**
     * サーバーを起動します
     */
    fun startServer() {
        embeddedServer(Netty, port) {
            setupServerModule()
        }.start()
    }

    /**
     * プロセスを開始します
     *
     * `main` 関数でこの関数を呼び出してください
     */
    fun launch() {
        setupDatabase()
        startServer()
    }
}