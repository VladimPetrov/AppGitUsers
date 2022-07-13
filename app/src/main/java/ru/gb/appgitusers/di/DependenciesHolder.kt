package ru.gb.appgitusers.di

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

class DependenciesHolder {
    private val dependenciesMap = HashMap<KClass<*>, Any>()
    fun <T:Any> get(clazz: KClass<T>): T {
        if (dependenciesMap.containsKey(clazz)) {
            return dependenciesMap[clazz] as T
        }
        else {
            throw IllegalArgumentException("Unknown Class")
        }
    }
    fun <T:Any>add(clazz: KClass<T>, dependencies: Any) {
        dependenciesMap[clazz] = dependencies
    }
}