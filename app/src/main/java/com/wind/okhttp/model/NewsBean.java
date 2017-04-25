package com.wind.okhttp.model;

import java.util.List;

/**
 * Created by zhangcong on 2017/4/25.
 */

public class NewsBean {
    public String reason;
    public Second result;
    public  class  Second{
        public String stat;
        public List<Third> data;
        public  class Third{
            public String title;
            public String date;
            public  String url;
            public String author_name;
            public String thumbnail_pic_s;
        }
    }
}
