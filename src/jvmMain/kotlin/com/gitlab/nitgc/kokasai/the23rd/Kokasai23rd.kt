package com.gitlab.nitgc.kokasai.the23rd

import com.gitlab.nitgc.kokasai.flowerkt.*
import com.gitlab.nitgc.kokasai.the23rd.routes.*
import com.gitlab.nitgc.kokasai.the23rd.routes.html.*
import io.ktor.routing.*

object Kokasai23rd: FlowerKt {
    override val databaseUrl = "jdbc:sqlite:.data.db"
    override val port = 8080
    override val routeBuilder = HtmlRouteBuilder
    override val routeBuildAction: Routing.() -> Unit = {
        cssRoutes()
        staticRoute()
        webSocketRoute()

        testRoute()
    }
}