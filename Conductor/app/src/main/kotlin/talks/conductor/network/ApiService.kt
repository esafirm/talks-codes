package talks.conductor.network

import io.reactivex.Single
import talks.conductor.data.UserAgent
import retrofit2.http.GET

interface ApiService {

    @GET("user-agent")
    fun getUserAgent(): Single<UserAgent>

}
