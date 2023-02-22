package com.example.nyc_school.data.api_service.result

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResultAdapter<S : Any>(
    private val successType: Type,
) : CallAdapter<S, Call<Result<S>>> {//CallAdapter<Any, Call<Result<*>>>

    //getParameterUpperBound(0, upperBound)
    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<Result<S>> {//Call<Result<*>>
        //ResultCall(call) as Call<Result<*>>
        return ResultCall(call)
    }
}