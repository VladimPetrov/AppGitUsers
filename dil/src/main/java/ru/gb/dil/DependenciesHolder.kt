package ru.gb.dil

import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

class DependenciesHolder {
    private val dependenciesMap = HashMap<KClass<*>, DependenciesFabric<*>>()
    fun <T : Any> get(clazz: KClass<T>): T {
        val dependenciesFabric = dependenciesMap[clazz]
        if (dependenciesFabric != null) {
            return dependenciesFabric.get() as T
        } else {
            throw IllegalArgumentException("Unknown Class")
        }
    }

    inline fun <reified T : Any> get(): T {
        return get(T::class)
    }

    fun <T : Any> add(clazz: KClass<T>, dependencies: DependenciesFabric<T>) {
        dependenciesMap[clazz] = dependencies
    }

    inline fun <reified T : Any> add(dependencies: DependenciesFabric<T>) {
        add(T::class,dependencies)
    }
}

abstract class DependenciesFabric<T:Any>(protected val creator: () -> Any) {
    abstract fun get(): Any
}

class Fabric<T:Any>(creator: () -> Any) : DependenciesFabric<T>(creator) {
    override fun get(): Any = creator()
}

class Singleton<T:Any>(creator: () -> Any) : DependenciesFabric<T>(creator) {
    private val dependency: Any by lazy { creator.invoke() }
    override fun get(): Any = dependency
}
