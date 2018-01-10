package com.ai.parse_row_data.service.impl;

/**
 * @Author:qmfang
 * @Description:
 * @Date:Created in 12:55 2018/1/10
 * @Modified By:
 */
public abstract  class ParseMailContent {

    abstract void parse2BaseLines(String s);
    abstract void parse2Plans(String s);
    abstract void parse2UserPortfolios(String s);
    abstract void parse2UserTrades(String s);

    /**
     * 此处需要实现解析邮件内容的接口
     */
    public void parseMail(String content){
        //Todo
    }
}
