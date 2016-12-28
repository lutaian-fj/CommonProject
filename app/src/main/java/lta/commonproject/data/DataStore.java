package lta.commonproject.data;

import lta.commonproject.data.config.ServerConfig;
import lta.commonproject.data.entity.GankResultEntity;
import lta.commonproject.data.server.api.ObservableServerApi;
import rx.Observable;

/**
 * @author: lutaian
 * @ClassName:
 * @Description:
 * @date: 2016/12/26
 */

public class DataStore {
    private final ObservableServerApi mServerApi;
    private static DataStore sInstance;
    public DataStore() {
        mServerApi = ServerConfig.getRetrofit().create(ObservableServerApi.class);
    }

    /**
     * @Title: getInstance
     * @Description: 获取DataStore对象
     * @param:
     * @return: sInstance
     * @throws:
     */
    public static DataStore getInstance() {
        if (sInstance == null) {
            sInstance = new DataStore();
        }
        return sInstance;
    }

    public static void releaseInstance() {
        sInstance = null;
    }

    public Observable<GankResultEntity> getAndroidData(int page) {
        Observable<GankResultEntity> result = mServerApi.getAndroidData(page);
        return result;
    }
}
