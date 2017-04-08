package com.dkunc.cmn.service;
import java.util.List;

public class FileVO {
    /**
     * TODO 파일 참조
     * /Lime/src/main/java/egovframework/com/cmm/service/FileVO.java
     */
    private int          sn;          // 순번
    private String       searchSn;    // 조회용 순번
    private String       fileSj;      // 파일 제목
    private String       fileCntnts;  // 파일 콘텐츠
    private String       fileCmmnCd;  // 파일 공통 코드
    private String       fileCbx;     // 파일 체크박스 - 노출 여부로 사용
    private String       fileUrl;     // 파일경로
    private String       fileNm;      // 파일명
    private String       fileStoredNm; // 저장파일명
    
    // JSON FORMAT
    private String       imageurl;    // 저장파일 주소 = originalurl 가 같음
    private long         filesize;    // 노출 파일 사이즈
    private String       imagealign;  // 이미지 정렬
    private String       originalurl; // 저장파일 주소
    private String       thumburl;    // 썸네일파일 주소
    private String       width;       // 이미지 가로 사이즈
    private String       height;      // 이미지 세로 사이즈
    private String       saveFileNm;  // 첨부파일 저장된 이름
    
    // 다중 파일 저장시
    private List<FileVO> fileVoList;
    public int getSn() {
        return sn;
    }
    public void setSn(int sn) {
        this.sn = sn;
    }
    public String getSearchSn() {
        return searchSn;
    }
    public void setSearchSn(String searchSn) {
        this.searchSn = searchSn;
    }
    public String getFileSj() {
        return fileSj;
    }
    public void setFileSj(String fileSj) {
        this.fileSj = fileSj;
    }
    public String getFileCntnts() {
        return fileCntnts;
    }
    public void setFileCntnts(String fileCntnts) {
        this.fileCntnts = fileCntnts;
    }
    public String getFileCmmnCd() {
        return fileCmmnCd;
    }
    public void setFileCmmnCd(String fileCmmnCd) {
        this.fileCmmnCd = fileCmmnCd;
    }
    public String getFileCbx() {
        return fileCbx;
    }
    public void setFileCbx(String fileCbx) {
        this.fileCbx = fileCbx;
    }
    public String getFileUrl() {
        return fileUrl;
    }
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
    public String getFileNm() {
        return fileNm;
    }
    public void setFileNm(String fileNm) {
        this.fileNm = fileNm;
    }
    public String getFileStoredNm() {
        return fileStoredNm;
    }
    public void setFileStoredNm(String fileStoredNm) {
        this.fileStoredNm = fileStoredNm;
    }
    public List<FileVO> getFileVoList() {
        return fileVoList;
    }
    public void setFileVoList(List<FileVO> fileVoList) {
        this.fileVoList = fileVoList;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public long getFilesize() {
        return filesize;
    }
    public void setFileSize(long filesize) {
        this.filesize = filesize;
    }
    public String getImagealign() {
        return imagealign;
    }
    public void setImagealign(String imagealign) {
        this.imagealign = imagealign;
    }
    public String getOriginalurl() {
        return originalurl;
    }
    public void setOriginalurl(String originalurl) {
        this.originalurl = originalurl;
    }
    public String getThumburl() {
        return thumburl;
    }
    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }
    public String getWidth() {
        return width;
    }
    public void setWidth(String width) {
        this.width = width;
    }
    public String getHeight() {
        return height;
    }
    public void setHeight(String height) {
        this.height = height;
    }
    public String getSaveFileNm() {
        return saveFileNm;
    }
    public void setSaveFileNm(String saveFileNm) {
        this.saveFileNm = saveFileNm;
    }
}
