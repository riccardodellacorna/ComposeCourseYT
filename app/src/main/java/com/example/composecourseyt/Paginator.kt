package com.example.composecourseyt

interface Paginator <Key, Item> { //interfaccia che definisce il comportamento del paginator
    suspend fun loadNextItems()
    fun reset()
}