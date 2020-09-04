package com.gitlab.nitgc.kokasai.the23rd.extension

import com.gitlab.nitgc.kokasai.the23rd.constants.RoutePath
import io.ktor.application.ApplicationCall
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.util.pipeline.ContextDsl
import io.ktor.util.pipeline.PipelineInterceptor
import kotlinx.html.*

@HtmlTagMarker
inline fun FlowOrInteractiveOrPhrasingContent.a(href: RoutePath? = null, target: String? = null, classes: String? = null, crossinline block: A.() -> Unit = {}) = a(href?.fullpath, target, classes, block)

@ContextDsl
fun Route.route(path: RoutePath, build: Route.() -> Unit): Route = route(path.path, build)

@ContextDsl
fun Route.get(path: RoutePath, body: PipelineInterceptor<Unit, ApplicationCall>) = get(path.path, body)

suspend inline fun ApplicationCall.respondRedirect(url: RoutePath, permanent: Boolean = false) = respondRedirect(url.fullpath, permanent)