package com.gitlab.nitgc.kokasai.the23rd.routes

import com.gitlab.nitgc.kokasai.flowerkt.route.*
import com.gitlab.nitgc.kokasai.the23rd.routes.template.WithHeaderTemplate.Companion.headerCss
import kotlinx.css.*

object HtmlRoute {
    object Index: RoutePath("/")
    object Login: RoutePath("/login")
    object Logout: RoutePath("/logout")
    object Account: RoutePath("/account")
    object Access: RoutePath("/access") {
        object Bus: RoutePath(this, "/bus")
    }

    object Api: RoutePath("/api") {
        object Bus: RoutePath(this, "/bus") {
            object Challenge: RoutePath(this, "/challenge")
            object Route: RoutePath(this, "/route")
        }
    }

    sealed class Css(path: String, val response: RuleSet?): RoutePath(path) {
        object Header: Css("/header.css", headerCss)
    }

    sealed class Js(path: String): RoutePath(path) {
        object MainBundle: Js("/main.bundle.js")
    }
}

object WebSocketRoute {
    object Bus: RoutePath("/bus")
}