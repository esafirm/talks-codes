package nolambda.state.network

import io.reactivex.Single
import nolambda.state.data.UserAgent
import retrofit2.http.GET

interface ApiService {

    @GET("user-agent")
    fun getUserAgent(): Single<UserAgent>

}
