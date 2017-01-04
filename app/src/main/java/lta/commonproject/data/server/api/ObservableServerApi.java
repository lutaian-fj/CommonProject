package lta.commonproject.data.server.api;

import lta.commonproject.data.entity.GankResultEntity;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description: 网络请求接口定义
 * @date: 2016/12/26
 */

public interface ObservableServerApi {
    @GET("all/20/{page}")
    Observable<GankResultEntity> getAndroidData(@Path("page") int page);
}
