package io.nutz.demo.module

import org.nutz.dao.Cnd
import org.nutz.dao.Dao
import org.nutz.dao.QueryResult
import org.nutz.dao.pager.Pager
import org.nutz.ioc.loader.annotation.Inject
import org.nutz.ioc.loader.annotation.IocBean
import org.nutz.lang.Strings
import org.nutz.lang.util.NutMap
import org.nutz.mvc.adaptor.JsonAdaptor
import org.nutz.mvc.annotation.AdaptBy
import org.nutz.mvc.annotation.At
import org.nutz.mvc.annotation.Ok
import org.nutz.mvc.annotation.POST
import org.nutz.mvc.annotation.Param

import io.nutz.demo.bean.User
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation

@Api("user")
@At("/user")
@IocBean
@Ok("json:full")
class UserModule {

    @Inject
    internal var dao: Dao? = null

    @ApiOperation(value = "获取用户总数", notes = "获取用户总数", httpMethod = "GET", response = Long::class)
    @Ok("raw")
    @At
    fun count(): Long {
        return dao!!.count(User::class.java).toLong()
    }

    @ApiOperation(value = "查询用户列表", notes = "可分页", httpMethod = "GET")
    @ApiImplicitParams(ApiImplicitParam(name = "pageNumber", paramType = "query", value = "起始页是1", dataType = "int", required = false, defaultValue = "1"), ApiImplicitParam(name = "pageSize", paramType = "query", value = "每页数量", dataType = "int", required = false, defaultValue = "20"))
    @At
    fun query(@Param("..") pager: Pager): NutMap {
        val users = dao!!.query(User::class.java, Cnd.orderBy().desc("id"), pager)
        pager.recordCount = dao!!.count(User::class.java)
        return NutMap("ok", true).setv("data", QueryResult(users, pager))
    }

    @At
    @POST
    fun add(@Param("..") user: User): NutMap {
        if (Strings.isBlank(user.name))
            return NutMap("ok", false)
        dao!!.insert(user)
        return NutMap("ok", true).setv("data", user)
    }

    @At
    @POST
    fun delete(id: Long): NutMap {
        dao!!.clear(User::class.java, Cnd.where("id", "=", id))
        return NutMap("ok", true)
    }

    @At
    @POST
    @AdaptBy(type = JsonAdaptor::class)
    fun update(@Param("..") user: User): NutMap {
        dao!!.update(user)
        return NutMap("ok", true)
    }
}
