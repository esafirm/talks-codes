package nolambda.state.di.helpers

interface HasComponent<T> {
    fun getComponent(): T
}
