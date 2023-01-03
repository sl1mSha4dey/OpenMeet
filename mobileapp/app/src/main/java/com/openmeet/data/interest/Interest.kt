package com.openmeet.data.interest

import com.openmeet.shared.data.storage.IEntity
import java.util.HashMap

data class Interest(var id: Int = 0, var description: String? = null) : IEntity {

    companion object {
        const val INTEREST = "Interest"
    }

    override fun toHashMap(): HashMap<String, *> {

        val map = HashMap<String, Any>()
        map["id"] = id
        map["description"] = description!!

        return map
    }
}