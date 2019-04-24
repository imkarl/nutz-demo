package io.nutz.demo

import org.nutz.boot.NbApp
import org.nutz.ioc.impl.PropertiesProxy
import org.nutz.ioc.loader.annotation.*
import org.nutz.mvc.annotation.*
import io.nutz.demo.bean.User
import org.nutz.dao.Dao

@IocBean(create = "init", depose = "depose")
class MainLauncher {

    @Inject
    protected var conf: PropertiesProxy? = null
    @Inject
    protected var dao: Dao? = null

    @At("/")
    @Ok("->:/index.html")
    fun index() {
    }

    fun init() {
        // NB自身初始化完成后会调用这个方法
        dao!!.create(User::class.java, false)
        if (dao!!.count(User::class.java) == 0) {
            val user = User()
            user.name = "wendal"
            user.age = 18
            user.location = "广州"
            dao!!.insert(user)
        }
    }

    fun depose() {}

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            NbApp().setArgs(args).setPrintProcDoc(true).run()
        }
    }

}
