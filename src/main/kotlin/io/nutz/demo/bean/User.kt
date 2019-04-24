package io.nutz.demo.bean

import org.nutz.dao.entity.annotation.Id
import org.nutz.dao.entity.annotation.Name
import org.nutz.dao.entity.annotation.Table

@Table("t_user")
class User {

    @Id
    var id: Long = 0
    @Name
    var name: String? = null
    var age: Int = 0
    var location: String? = null

    constructor() {}

    constructor(name: String, age: Int, location: String) : super() {
        this.name = name
        this.age = age
        this.location = location
    }
}
