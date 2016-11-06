package com.open.demo.config;

import com.open.demo.utils.AsyncTaskDataLoad;

import java.util.List;
import java.util.Map;

/**
 * Created by mac on 2016/10/18.
 */

public class Config {
    private Config() {
    }

//    public static final String YOUTUBE_API_KEY = "AIzaSyAP1H0PtjMyfu1FZZs10-TEklKgesvEpQw";
    public static final String YOUTUBE_API_KEY = "AIzaSyCbl1b5Ga6q3u3vv41zt4egLAmnatkp5mU";
    public static  final String YOUTUBE_CHANNEL_ID = "UCJnvK2B5QvnT70ZK_an3eMg";

    public static Map<String, List<String>> playListStr = null;

    public static String updatedToday = "";


    public static void initPlayListDatas() {
        AsyncTaskDataLoad atdl = new AsyncTaskDataLoad(playListStr);
        atdl.execute();
    }
}
