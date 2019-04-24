package io.nutz.demo.module


import org.nutz.ioc.impl.PropertiesProxy
import org.nutz.ioc.loader.annotation.*
import org.nutz.mvc.annotation.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

@Api("time")
@At("/time")
@IocBean(create = "init", depose = "depose")
class TimeModule {

    @Inject
    protected var conf: PropertiesProxy? = null

    @ApiOperation(value = "获取当前毫秒数", notes = "服务器端的时间", httpMethod = "GET", response = Long::class)
    @At
    @Ok("raw")
    fun now(): Long {
        return System.currentTimeMillis()
    }

    fun init() {}
    fun depose() {}

}
