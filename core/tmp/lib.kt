package lib

class Provider<T>(val _value: T) {
    inline operator fun provideDelegate(thisRef: Any?, kProperty: Any) =
        Mut(_value)
}

class Mut<T>(var _value: T) {

    inline operator fun getValue(thisRef: Any?, kProperty: Any) = _value

    inline operator fun setValue(thisRef: Any?, kProperty: Any, newValue: T) {
        _value = newValue
    }
}