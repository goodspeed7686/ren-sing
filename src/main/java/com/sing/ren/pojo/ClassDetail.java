package com.sing.ren.pojo;public class ClassDetail extends BasePOJO{	private Integer classDetailId;	private Integer classMasterId;	private String studentId;	private String teacherId;	private String song;	private String date;	private String time;	private String hw;	private String teacherNote;	private String studentNote;	public Integer getClassDetailId() {		return classDetailId;	}	public void setClassDetailId(Integer classDetailId) {		this.classDetailId = classDetailId;	}	public Integer getClassMasterId() {		return classMasterId;	}	public void setClassMasterId(Integer classMasterId) {		this.classMasterId = classMasterId;	}	public String getStudentId() {		return studentId;	}	public void setStudentId(String studentId) {		this.studentId = studentId;	}	public String getTeacherId() {		return teacherId;	}	public void setTeacherId(String teacherId) {		this.teacherId = teacherId;	}	public String getSong() {		return song;	}	public void setSong(String song) {		this.song = song;	}	public String getDate() {		return date;	}	public void setDate(String date) {		this.date = date;	}	public String getTime() {		return time;	}	public void setTime(String time) {		this.time = time;	}	public String getHw() {		return hw;	}	public void setHw(String hw) {		this.hw = hw;	}	public String getTeacherNote() {		return teacherNote;	}	public void setTeacherNote(String teacherNote) {		this.teacherNote = teacherNote;	}	public String getStudentNote() {		return studentNote;	}	public void setStudentNote(String studentNote) {		this.studentNote = studentNote;	}}