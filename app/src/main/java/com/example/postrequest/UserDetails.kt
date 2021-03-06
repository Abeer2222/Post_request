package com.example.postrequest

import com.google.gson.annotations.SerializedName
class UserDetails {

    var data:List<User>?=null

    class User {
        @SerializedName("name")
        var name:String?=null

        @SerializedName("location")
        var location:String?=null

        constructor(name: String?, location: String?) {
            this.name = name
            this.location = location
        }

    }
}