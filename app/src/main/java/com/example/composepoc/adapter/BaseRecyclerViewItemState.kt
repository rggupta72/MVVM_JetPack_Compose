package com.example.composepoc.adapter

interface BaseRecyclerViewItemState<T> {
    val viewType: T
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}