package com.example.chitthi

import android.provider.ContactsContract.CommonDataKinds.Email


//this class is used to just store the values of the users

/*Since we are using firebase so we can not use data class to store values*/
class User {
    var name:String? = null
    var email: String? = null
    var uid: String? = null

    //ther will be an empty constructor for firebase
    constructor(){}

    constructor(name: String?,email: String?,uid: String?){
        this.name = name
        this.email = email
        this.uid = uid
    }

}