package com.examportal.server.Request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class Exam_File_Request {
    private String tenKyThi;
    private String loaiDeThi;
    private String maDeThi;
    private Integer thoiGianLamBai;
    private String maKyThi;
    private String matKhauKyThi;
    private MultipartFile file;
    private List<Answer> answers;
    private int totalScore;

    public Exam_File_Request() {
    }

    public Exam_File_Request(String tenKyThi, String loaiDeThi, String maDeThi, Integer thoiGianLamBai, String maKyThi, String matKhauKyThi, MultipartFile file, List<Answer> answers, int totalScore) {
        this.tenKyThi = tenKyThi;
        this.loaiDeThi = loaiDeThi;
        this.maDeThi = maDeThi;
        this.thoiGianLamBai = thoiGianLamBai;
        this.maKyThi = maKyThi;
        this.matKhauKyThi = matKhauKyThi;
        this.file = file;
        this.answers = answers;
        this.totalScore = totalScore;
    }

    public String getTenKyThi() {
        return tenKyThi;
    }

    public void setTenKyThi(String tenKyThi) {
        this.tenKyThi = tenKyThi;
    }

    public String getLoaiDeThi() {
        return loaiDeThi;
    }

    public void setLoaiDeThi(String loaiDeThi) {
        this.loaiDeThi = loaiDeThi;
    }

    public String getMaDeThi() {
        return maDeThi;
    }

    public void setMaDeThi(String maDeThi) {
        this.maDeThi = maDeThi;
    }

    public Integer getThoiGianLamBai() {
        return thoiGianLamBai;
    }

    public void setThoiGianLamBai(Integer thoiGianLamBai) {
        this.thoiGianLamBai = thoiGianLamBai;
    }

    public String getMaKyThi() {
        return maKyThi;
    }

    public void setMaKyThi(String maKyThi) {
        this.maKyThi = maKyThi;
    }

    public String getMatKhauKyThi() {
        return matKhauKyThi;
    }

    public void setMatKhauKyThi(String matKhauKyThi) {
        this.matKhauKyThi = matKhauKyThi;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }
}
