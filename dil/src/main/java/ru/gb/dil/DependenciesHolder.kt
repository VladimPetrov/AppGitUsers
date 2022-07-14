package ru.gb.dil

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

class DependenciesHolder {
    private val dependenciesMap = HashMap<KClass<*>, DependenciesFabric>()
    fun <T : Any> get(clazz: KClass<T>): T {
        val dependenciesFabric = dependenciesMap[clazz]
        if (dependenciesFabric != null) {
            return dependenciesFabric.get() as T
        } else {
            throw IllegalArgumentException("Unknown Class")
        }
    }

    fun <T : Any> add(clazz: KClass<T>, dependencies: DependenciesFabric) {
        dependenciesMap[clazz] = dependencies
    }

}

abstract class DependenciesFabric(protected val creator: () -> Any) {
    abstract fun get(): Any
}

class Fabric(creator: () -> Any) : DependenciesFabric(creator) {
    override fun get(): Any = creator()
}

class Singleton(creator: () -> Any) : DependenciesFabric(creator) {
    private val dependency: Any by lazy { creator.invoke() }
    override fun get(): Any = dependency
}
