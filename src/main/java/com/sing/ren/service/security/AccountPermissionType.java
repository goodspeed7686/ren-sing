package com.sing.ren.service.security;

public enum AccountPermissionType {
	
	ADMIN(0),
	TEACHER(1),
	STUDENT(2),
	
	UNKNOWN(-1);
	
	private int type = -1;
	
	AccountPermissionType(int type) {
		this.type = type;
	}
	
	/**
	 * 是否為行政人員
	 * @return
	 */
	public boolean isAdmin(){
		return AccountPermissionType.ADMIN.compareTo(this) == 0;
	}
	
	/**
	 * 是否為老師
	 * @return
	 */
	public boolean isTeacher(){
		return AccountPermissionType.TEACHER.compareTo(this) == 0;
	}
	
	/**
	 * 是否為學生
	 * @return
	 */
	public boolean isStudent(){
		return AccountPermissionType.STUDENT.compareTo(this) == 0;
	}
	
	public static AccountPermissionType valueOf(int permission){
		
		for (AccountPermissionType permissionType : AccountPermissionType.values()){
			
			if (permissionType.getType() == permission)
				return permissionType;
			
		}
		
		return AccountPermissionType.UNKNOWN;
	}
	
	public int getType() {
		return type;
	}
}
