package com.carboncapture.carbon.core;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * @author Santa Antilles
 * @description
 * @date 2024/2/28-19:36:42
 */
@Data
public class TableDataInfo implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    private long total;

    /** 列表数据 */
    private List rows;

    /** 消息状态码 */
    private int code;

    /** 消息内容 */
    private String msg;

    public static TableDataInfo success(List<?> data, int total){
        TableDataInfo ret = new TableDataInfo();
        ret.setCode(HttpStatus.SUCCESS);
        ret.setMsg("success");
        ret.setRows(data);
        ret.setTotal(total);
        return ret;
    }

    public static TableDataInfo pagination(List<?> data, int total, int pageNum, int pageSize){
        TableDataInfo tableDataInfo = new TableDataInfo();
        int maxPages = total % pageSize == 0 ? total / pageSize: total / pageSize + 1;
//        System.out.println("maxPages: "+maxPages);
        // 请求数据过多
        if(pageNum>maxPages){
            tableDataInfo.setRows(null);
            tableDataInfo.setMsg("操作越界！");
            tableDataInfo.setCode(HttpStatus.SUCCESS);
            tableDataInfo.setTotal(total);
            return tableDataInfo;
        }
        // 请求最后一页
        if(pageNum==maxPages){
            tableDataInfo.setRows(data.subList((pageNum-1)*pageSize, total));
            tableDataInfo.setCode(HttpStatus.SUCCESS);
            tableDataInfo.setMsg("操作成功！");
            tableDataInfo.setTotal(total);
            return tableDataInfo;
        }
        tableDataInfo.setRows(data.subList((pageNum-1)*pageSize, pageNum*pageSize));
        tableDataInfo.setCode(HttpStatus.SUCCESS);
        tableDataInfo.setMsg("操作成功！");
        tableDataInfo.setTotal(total);
        return tableDataInfo;
    }
}
