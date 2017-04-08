package com.dkunc.cmn;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class CmmConst {
    public static final String FILE_SEPARATOR               = File.separator;

    public static final String WINDOWS_TEMP_JSON_DIR_PREFIX = "C:" + FILE_SEPARATOR + "temp" + FILE_SEPARATOR + "json" + FILE_SEPARATOR;
    public static final String LINUX_TEMP_JSON_DIR_PREFIX   = FILE_SEPARATOR + "opt" + FILE_SEPARATOR + "temp" + FILE_SEPARATOR + "json" + FILE_SEPARATOR;
    public static final String WINDOWS_FILE_DIR_PREFIX  = "C:" + FILE_SEPARATOR + "opt" + FILE_SEPARATOR + "inline" + FILE_SEPARATOR;
    public static final String LINUX_FILE_DIR_PREFIX    = FILE_SEPARATOR + "opt" + FILE_SEPARATOR + "file" + FILE_SEPARATOR;
   
    /**
     * 파일의 용도 구분
     * @author DKUNC
     *
     */
    public enum FILE_USAGE_TYPE{
        ATTACH("attach","첨부파일"),
        INLINE("inline","인라인 이미지");
        private String id;
        private String etc;
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getEtc() {
            return etc;
        }
        public void setEtc(String etc) {
            this.etc = etc;
        }
        private FILE_USAGE_TYPE(String id, String etc) {
            this.id = id;
            this.etc = etc;
        }
    }
}