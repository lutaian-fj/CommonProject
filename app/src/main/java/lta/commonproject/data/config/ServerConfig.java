package lta.commonproject.data.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2016/12/26
 */

public class ServerConfig {
    private static Retrofit mRetrofit;
    private static Gson mGson;
    private static OkHttpClient mOkHttpClient;

    /**
     * @Title: getRetrofit
     * @Description: 获取Retrofit对象
     * @param:
     * @return: mRetrofit
     * @throws:
     */
    public static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            buildRetrofit();
        }
        return mRetrofit;
    }

    /**
     * @Title: getGson
     * @Description: 获取Gson对象
     * @param:
     * @return: mGson
     * @throws:
     */
    public static Gson getGson() {
        if (mGson == null) {
            buildRetrofit();
        }
        return mGson;
    }

    /**
     * @Title: getClient
     * @Description: 获取OkHttpClient对象
     * @param:
     * @return: mOkHttpClient
     * @throws:
     */
    public static OkHttpClient getClient() {
        if (mOkHttpClient == null) {
            buildRetrofit();
        }
        return mOkHttpClient;
    }

    /**
     * @Title: buildRetrofit
     * @Description: 赋值
     * @param:
     * @return:
     * @throws:
     */
    private static synchronized void buildRetrofit() {
        if (mGson == null) {
            mGson = new GsonBuilder()
//                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
//                    .registerTypeAdapter(Date.class, new JsonSerializer<Date>() {
//                        @Override
//                        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
//                            return new JsonPrimitive(src.getTime());
//                        }
//                    })
//                    .excludeFieldsWithoutExposeAnnotation()
//                    .registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
//                        @Override
//                        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
//                            if (src == src.longValue()) {
//                                return new JsonPrimitive(src.longValue());
//                            }
//                            return new JsonPrimitive(src);
//                        }
//                    })
                    .create();
        }
        if (mOkHttpClient == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            mOkHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .retryOnConnectionFailure(true)
                    .connectTimeout(15, TimeUnit.SECONDS)
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request request = chain.request();
//                            OkRequestDelegateImpl requestDelegate = new OkRequestDelegateImpl(request);
//                            try {
//                                // 添加UC 认证头信息
//                                SecurityDelegate.getInstance().handleBeforeSend(requestDelegate);
//                            } catch (ResourceException e) {
//                                e.printStackTrace();
//                            }
//
//                            String apiUserId = DataStore.getInstance().getApiUserId();
//                            if (TextUtils.equals(apiUserId, String.valueOf(CommonUtil.getCurrentUserId()))) {
//                                apiUserId = "";
//                            }
//                            requestDelegate.setRequestHead("principal",apiUserId);
//                            Response response = chain.proceed(requestDelegate.getRequest());
//                            return response;
//                        }
//                    })
//                    .addInterceptor(new Interceptor() {
//                        @Override
//                        public Response intercept(Chain chain) throws IOException {
//                            Request request = chain.request()
//                                    .newBuilder()
//                                    .addHeader("apikey","d5d7566b4e34657b7b9fc386a3687563")
//                                    .build();
//                            return chain.proceed(request);
//                        }
//                    })
                    .build();
        }

        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .baseUrl("http://apis.baidu.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(mGson))
                    .build();
        }
    }

    public static synchronized void init() {
        buildRetrofit();
    }

}
