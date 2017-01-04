package lta.commonproject.data.server.api;

import lta.commonproject.data.entity.PicResultEntity;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description: 网络请求接口定义
 * @date: 2016/12/26
 */

public interface ObservableServerApi {
    @Headers("apikey:d5d7566b4e34657b7b9fc386a3687563")
    @GET("txapi/mvtp/meinv")
    Observable<PicResultEntity> getAndroidData(@Query("num") int page);
}
