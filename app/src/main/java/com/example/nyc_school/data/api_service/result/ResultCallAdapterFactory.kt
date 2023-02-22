package com.example.nyc_school.data.api_service.result

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

// https://blog.canopas.com/retrofit-effective-error-handling-with-kotlin-coroutine-and-result-api-405217e9a73d
class ResultCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        /////

        /////
        // suspend functions wrap the response type in `Call`
        if (getRawType(returnType) != Call::class.java || returnType !is ParameterizedType) {
            return null
        }

        // 1
        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as Call<NetworkResponse<<Foo>> or Call<NetworkResponse<out Foo>>"
        }

        // get the response type inside the `Call` type
        val upperBound = getParameterUpperBound(0, returnType)

        // 2
        // if the response type is not ApiResponse then we can't handle this type, so we return null
        if (getRawType(upperBound) != Result::class.java) {
            return null
        }

        // 3
        // the response type is ApiResponse and should be parameterized
        check(upperBound is ParameterizedType) { "Response must be parameterized as NetworkResponse<Foo> or NetworkResponse<out Foo>" }

        // 4
        val successBodyType = getParameterUpperBound(0, upperBound)
//        val errorBodyType = getParameterUpperBound(1, upperBound)

//        val errorBodyConverter =
//            retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return ResultAdapter<Any>(successBodyType)

//        return if (upperBound is ParameterizedType && upperBound.rawType == Result::class.java) {
//            object : CallAdapter<Any, Call<Result<*>>> {
//                override fun responseType(): Type = getParameterUpperBound(0, upperBound)
//
//                override fun adapt(call: Call<Any>): Call<Result<*>> =
//                    ResultCall(call) as Call<Result<*>>
//            }
//        } else {
//            Log.d("ResultCallAdapterFactory", ">>> null 2")
//            null
//        }
    }
}