package ru.gb.dil

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

class DependenciesHolder {
    private val dependenciesMap = HashMap<KClass<*>, DependenciesFabric>()
    fun <T : Any> get(clazz: KClass<T>): T {
        if (dependenciesMap.containsKey(clazz) != null) {
            return dependenciesMap[clazz] as T
        } else {
            throw IllegalArgumentException("Unknown Class")
        }
    }

    fun <T : Any> add(clazz: KClass<T>, dependencies: DependenciesFabric) {
        dependenciesMap[clazz] = dependencies
    }

    fun <T : Any> add(dependencies: DependenciesFabric) {
        dependenciesMap[dependencies::class] = dependencies
    }
}

abstract class DependenciesFabric(protected val creator : () -> Any) {
    abstract fun get():Any
}

class Fabric (creator: () -> Unit):DependenciesFabric(creator) {
    override fun get(): Any = creator()
}
class Singleton (creator: () -> Unit): DependenciesFabric(creator) {
    private val dependency: Any by lazy { creator.invoke() }
    override fun get(): Any = dependency
}
