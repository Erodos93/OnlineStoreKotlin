package com.example.onlinestorekotlin

class TemporaryCart {
    var id:Int
    var name:String
    var price:Int
    var email:String
    var amount:Int

    constructor(id: Int, name: String, price: Int, email: String, amount: Int) {
        this.id = id
        this.name = name
        this.price = price
        this.email = email
        this.amount = amount
    }
}