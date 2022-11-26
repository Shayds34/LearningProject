package com.sederikkuapplication.shared

object Query {

        // from @Query : Number of items to skip for the result set
        const val FROM_FIRST_INDEX = "0"
        // size @Query : Number of items to retrieve
        const val MAX_SIZE = "10000"
        const val WITH_OPERATIONS_MAX_SIZE = "50"
        // status @Query : Status of the transaction (success / pending / invalid / fail)
        const val STATUS_SUCCESS = "success"
        // order @Query : Sort order (asc/desc)
        const val ORDER_ASC = "asc"
        const val ORDER_DESC = "desc"
        // order withOperations : Return operations for transactions
        const val WITH_OPERATIONS = "true"
        const val WITHOUT_OPERATIONS = "false"

}