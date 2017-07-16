package io.mikael.talent.model

import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.usertype.UserType
import java.io.Serializable
import java.sql.PreparedStatement
import java.sql.ResultSet

open class SqlStringArray : UserType {

    override fun hashCode(x: Any?) = x?.hashCode() ?: 0

    override fun equals(x: Any?, y: Any?) = x == y

    override fun replace(original: Any?, target: Any?, owner: Any?) = original ?: mutableListOf<String>()

    override fun returnedClass() = MutableList::class.java

    override fun assemble(cached: Serializable?, owner: Any?) = cached ?: mutableListOf<String>()

    override fun disassemble(value: Any?) = value as Serializable

    override fun isMutable() = false

    override fun sqlTypes() = intArrayOf(java.sql.Types.ARRAY)

    @Suppress("UNCHECKED_CAST")
    override fun deepCopy(value: Any?): Any {
        val ret = mutableListOf<String>()
        if (value != null && value is MutableList<*>) {
            return ret.addAll(value as MutableList<String>)
        } else {
            return ret
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun nullSafeGet(rs: ResultSet?, names: Array<out String>?, session: SessionImplementor, owner: Any): Any {
        val a1 = rs?.getArray(names?.get(0))?.array as Array<Any?>?
        return a1?.map { it.toString() }?.toMutableList() ?: mutableListOf<String>()
    }

    override fun nullSafeSet(st: PreparedStatement, value: Any, index: Int, session: SessionImplementor) {
        if (value !is MutableList<*>) return
        st.setArray(index, session.connection().createArrayOf("string", value.toTypedArray()))
    }

}
