package com.sing.ren.pojo;public class AchievementLog extends BasePOJO{	private Integer achievementLogId;	private Integer achievementId;	private String studentId;	private String grade;	private String score;	private String date;	public Integer getAchievementLogId() {		return achievementLogId;	}	public void setAchievementLogId(Integer achievementLogId) {		this.achievementLogId = achievementLogId;	}	public Integer getAchievementId() {		return achievementId;	}	public void setAchievementId(Integer achievementId) {		this.achievementId = achievementId;	}	public String getStudentId() {		return studentId;	}	public void setStudentId(String studentId) {		this.studentId = studentId;	}	public String getGrade() {		return grade;	}	public void setGrade(String grade) {		this.grade = grade;	}	public String getScore() {		return score;	}	public void setScore(String score) {		this.score = score;	}	public String getDate() {		return date;	}	public void setDate(String date) {		this.date = date;	}}