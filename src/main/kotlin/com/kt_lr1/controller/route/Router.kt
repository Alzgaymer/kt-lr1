package com.kt_lr1.controller.route

private typealias PathType = Int
private typealias Handler = ()-> Unit

class Router (
    private val routes: MutableMap<PathType, Handler> = mutableMapOf(),
) {
    fun addMethod(path: PathType, method: Handler) {
        routes[path] = method
    }
    fun route(path: PathType) {
        routes[path]?.let { it() }
    }
}