package com.example.signup

interface ItemActionListener{
    fun onItemMoved(from:Int, to:Int)
    fun onItemSwiped(position:Int)
}