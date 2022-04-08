package com.longsys.export.util.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.longsys.export.constant.ExcelConstant;
import com.longsys.export.constant.SpecialInfoConstant;
import com.longsys.export.constant.benum.ExceptionEnum;
import com.longsys.export.domain.bo.ColumnExportBo;
import com.longsys.export.domain.bo.ExportBo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.longsys.export.constant.ExcelConstant.SHEET_NAME_FORMAT;

/**
 * ExcelUtil
 *
 * @author huan.yang
 * @date 2022-03-30
 */
@Slf4j
public class ExcelUtil {

    private static final String FILE_NAME_DATE_FOWMAT = "yyyyMMddHHmmss";

    private ExcelUtil() {
    }


    /**
     * 多表头下载文件
     *
     * @param request  请求
     * @param response 响应
     * @param fileName 文件名
     * @param exportBo 业务导出模型
     */
    public static void downLoad(HttpServletRequest request, HttpServletResponse response, String fileName, ExportBo exportBo) throws Exception {
        //设置请求头
        setReq(request, response, fileName);
        //写数据
        export(response, exportBo);
    }

    /**
     * 设置请求头
     *
     * @param request  请求
     * @param response 响应
     * @param fileName 字段名
     */
    public static void setReq(HttpServletRequest request, HttpServletResponse response, String fileName) throws Exception {
        final String userAgent = request.getHeader(SpecialInfoConstant.REQ_HEAD_AGENT);
        //response.setContentType("application/vnd.ms-excel"); // 改成输出excel文件
        response.setContentType(SpecialInfoConstant.RESP_CONTENTTYPE_DOWN);


        //设置请求头
        String finalFileName = null;
        // IE浏览器
        if (StringUtils.contains(userAgent, SpecialInfoConstant.MSIE)) {
            finalFileName = URLEncoder.encode(fileName, SpecialInfoConstant.ENCODE_UTF8);
        }
        // google,火狐浏览器
        else if (StringUtils.contains(userAgent, SpecialInfoConstant.MOZILLA)) {
            finalFileName = new String(fileName.getBytes(), SpecialInfoConstant.ENCODE_ISO);
        }
        // 其他浏览器
        else {
            finalFileName = URLEncoder.encode(fileName, SpecialInfoConstant.ENCODE_UTF8);
        }

        //开始下载标识
        Cookie cookie = new Cookie(SpecialInfoConstant.LOAD_HIDE, SpecialInfoConstant.TRUE);
        cookie.setPath(SpecialInfoConstant.SLASH);
        response.addCookie(cookie);

        String dataStr = new SimpleDateFormat(FILE_NAME_DATE_FOWMAT).format(new Date());

        // 这里设置一下让浏览器弹出下载提示框，而不是直接在浏览器中打开
        response.setHeader(SpecialInfoConstant.CONTENT_DISP, String.format(SpecialInfoConstant.HEAD_DOWN, finalFileName + dataStr));


    }


    public static void export(HttpServletResponse response, ExportBo exportBo) throws Exception {
        ExceptionEnum.asser(null == response || null == exportBo || CollectionUtils.isEmpty(exportBo.getExcelTitles()) || CollectionUtils.isEmpty(exportBo.getNodes()), ExceptionEnum.BUILD_EXCEL_ERROR);

        List<ColumnExportBo> nodes = exportBo.getNodes();
        Map<String, LinkedHashMap<String, String>> excelTitles = exportBo.getExcelTitles();

        ExcelWriter writer = EasyExcelFactory.getWriter(response.getOutputStream());

        int sheetNo = ExcelConstant.SHEET_START_INDEX;

        for (ColumnExportBo node : nodes) {
            LinkedHashMap<String, String> title = excelTitles.get(node.getKey());

            buildSheet(node, title, sheetNo, writer);

            sheetNo++;
        }
        writer.finish();
    }


    private static void buildSheet(ColumnExportBo node, LinkedHashMap<String, String> title, int sheetNo, ExcelWriter excelWriter) {
        Sheet sheet = new Sheet(sheetNo, ExcelConstant.DEFAULT_HEAD_LINE_MUN);
        sheet.setSheetName(String.format(SHEET_NAME_FORMAT, node.getKey(), node.getTableName()));
        sheet.setHead(getTitle(new ArrayList<>(title.values())));
        List<List<Object>> conversionRows = new ArrayList<>();
        List<Map<String, Object>> rows = node.getRows();

        for (Map<String, Object> row : rows) {
            List<Object> r = new ArrayList<>();
            for (String s : title.keySet()) {
                r.add(row.get(s));
            }
            conversionRows.add(r);
        }
        excelWriter.write1(conversionRows, sheet);
    }

    private static List<List<String>> getTitle(List<String> s){
        List<List<String>> r = new ArrayList<>();
        for (String a : s) {
            r.add(Collections.singletonList(a));
        }
        return r;
    }

}
