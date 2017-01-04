package lta.commonproject.data;

import lta.commonproject.data.config.ServerConfig;
import lta.commonproject.data.entity.PicResultEntity;
import lta.commonproject.data.server.api.ObservableServerApi;
import rx.Observable;

/**
 * @author: LuTaiAn
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
//        if (sInstance == null) {
//            sInstance = new DataStore();
//        }
//        return sInstance;
        return DataStoreHolder.instance;
    }

    private static class DataStoreHolder {
        private static final DataStore instance = new DataStore();
    }

    public static void releaseInstance() {
        sInstance = null;
    }

    public Observable<PicResultEntity> getAndroidData(int page) {
        Observable<PicResultEntity> result = mServerApi.getAndroidData(10);
        return result;
    }
}
