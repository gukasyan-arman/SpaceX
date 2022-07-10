package com.example.spacex.models

data class CardItemUiModel (
    val name: String,
    val date: String,
    val flight: Int,
    val success: Boolean,
    val avatarForRv: String,
)