package com.openmeet.data.interest

import com.openmeet.shared.data.storage.ResultSetExtractor
import java.sql.ResultSet

class InterestExtractor : ResultSetExtractor<Interest> {

    override fun extract(resultSet: ResultSet): Interest {

        val interest = Interest()

        interest.id = resultSet.getInt(Interest.INTEREST + ".id")
        interest.description = resultSet.getString(Interest.INTEREST + ".description")

        return interest
    }
}