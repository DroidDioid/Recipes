package ru.tim.recipes.core

/** Интерфейс конвертора, с типом [A], из которого конвертируется
 * и с типом [B], в который конвертируется. */
interface BaseMapper<in A, out B> {

    /** Выполняет конвертацию из [A] в [B]. */
    fun map(type: A?): B
}