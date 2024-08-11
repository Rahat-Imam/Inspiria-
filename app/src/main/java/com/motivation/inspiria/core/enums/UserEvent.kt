package com.motivation.inspiria.core.enums

sealed interface UserEvent {
    data class IsBookmark(val bookMarkValue: Int, val id: Int) : UserEvent
}