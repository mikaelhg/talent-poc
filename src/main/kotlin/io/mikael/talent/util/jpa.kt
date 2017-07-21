package io.mikael.talent.util

import org.hibernate.engine.spi.SessionImplementor
import org.hibernate.usertype.UserType
import java.io.Serializable
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * PostgreSQL / H2 array type.
 */
open class SqlStringArray : UserType {

    override fun hashCode(x: Any?): Int = x?.hashCode() ?: 0

    override fun equals(x: Any?, y: Any?): Boolean = x == y

    override fun replace(original: Any?, target: Any?, owner: Any?): Any
        = original ?: mutableListOf<String>()

    override fun returnedClass(): Class<MutableList<*>>
        = MutableList::class.java

    override fun assemble(cached: Serializable?, owner: Any?): Serializable
        = cached ?: mutableListOf<String>() as Serializable

    override fun disassemble(value: Any?): Serializable
        = value as Serializable

    override fun isMutable(): Boolean = false

    override fun sqlTypes(): IntArray = intArrayOf(java.sql.Types.ARRAY)

    @Suppress("UNCHECKED_CAST")
    override fun deepCopy(value: Any?): MutableList<String> {
        val ret = mutableListOf<String>()
        if (value != null && value is MutableList<*>) {
            ret.addAll(value as MutableList<String>)
        }
        return ret
    }

    @Suppress("UNCHECKED_CAST")
    override fun nullSafeGet(rs: ResultSet?, names: Array<out String>?, session: SessionImplementor, owner: Any): Any {
        val a1 = rs?.getArray(names?.get(0))?.array as Array<Any?>?
        return a1?.map { it.toString() }?.toMutableList() ?: mutableListOf<String>()
    }

    override fun nullSafeSet(st: PreparedStatement, value: Any?, index: Int, session: SessionImplementor) {
        if (value == null || value !is List<*>) return
        st.setArray(index, session.connection().createArrayOf("string", value.toTypedArray()))
    }

}
