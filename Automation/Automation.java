import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Automation {

	public static void main(String[] args) throws BiffException, IOException {
		Workbook workbook = Workbook.getWorkbook(new File("E:\\Project\\ren-sing\\excel\\classMaster.xls"));
		Sheet sheet = workbook.getSheet("ss");
		FileWriter fw;
	//	System.out.println(sheet.getCell(1, 1).getContents());
		String table_name=sheet.getCell(0, 0).getContents();
	//	System.out.println(sheet.getCell(0, 0).getContents());
	//	System.out.println(sheet.getColumns());
	//	System.out.println(sheet.getRows());
		String [] arry=new String[sheet.getRows()];     //欄位名稱
		for(int i=0;i<sheet.getRows();i++){             
			if(sheet.getCell(1,i).getContents()!=null){
				arry[i]=sheet.getCell(1,i).getContents();
			}
		}
		String [] arry2=new String[sheet.getRows()];    //資料型態
		for(int i=0;i<sheet.getRows();i++){
			if(sheet.getCell(2,i).getContents()!=null){
				arry2[i]=sheet.getCell(2,i).getContents();
			}
		}
		workbook.close();
		//========== 創建xml by table==============
		fw = new FileWriter("E:\\workspace\\workspace_ren-sing\\ren-sing\\src\\main\\resources\\sqlmap\\by_table\\"+turnUpcase(table_name)+".xml");
		fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r");
		fw.write("<!DOCTYPE sqlMap PUBLIC \"-//ibatis.apache.org//DTD SQL Map 2.0//EN\" \"http://ibatis.apache.org/dtd/sql-map-2.dtd\">\r");
		fw.write("<sqlMap namespace=\"ren.sing.dao.table\">\r\r");
		fw.write("	<insert id=\""+table_name+"_insert\" parameterClass=\"com.sing.ren.pojo."+turnUpcase1(table_name)+"\">\r");
		fw.write("		<![CDATA[\r			INSERT INTO "+upcase(table_name)+" (\r");
		for(int i=0;i<arry.length;i++){
			if(i==arry.length-1){
				fw.write("				"+arry[i]+"\r");
			}else{
				fw.write("				"+arry[i]+",\r");
			}
		}
		fw.write("				) VALUES (\r");
		for(int i=0;i<arry.length;i++){
			if(i==arry.length-1){
				fw.write("				#"+turnUpcase(arry[i])+"#\r");
			}else{
				fw.write("				#"+turnUpcase(arry[i])+"#,\r");
			}
		}
		fw.write("			)\r		]]>\r");
		fw.write("	</insert>\r");
		fw.write("	<select id=\""+table_name+"_query\" resultClass=\"java.util.HashMap\" parameterClass=\"com.sing.ren.pojo."+turnUpcase1(table_name)+"\">\r");
		fw.write("		<![CDATA[\r			SELECT ");
		for(int i=0;i<arry.length;i++){
			if(i==arry.length-1){
				fw.write(arry[i]+"\r");
			}else{
				fw.write(arry[i]+",");
			}
		}
		fw.write("			FROM "+upcase(table_name)+"\r			WHERE 1\r		]]>\r");
		for(int i=0;i<arry.length;i++){
			fw.write("		<isNotEmpty prepend=\"AND\" property=\""+turnUpcase(arry[i])+"\">\r			<![CDATA[ "+arry[i]+" = #"+turnUpcase(arry[i])+"#  ]]>\r		</isNotEmpty>\r");
		}
		fw.write("\r		<isNotEmpty property=\"order\">\r			<![CDATA[	ORDER BY $order$	]]>\r		</isNotEmpty>\r		<isNotEmpty property=\"rowLimit\">\r			<isNotEmpty prepend=\"LIMIT\" property=\"fromIndex\">\r");
		fw.write("				<![CDATA[ $fromIndex$, $rowLimit$ ]]>\r			</isNotEmpty>\r			<isEmpty prepend=\"LIMIT\" property=\"fromIndex\">\r				<![CDATA[ LIMIT $rowLimit$ ]]>\r			</isEmpty>\r		</isNotEmpty>\r	<!--  	<include refid=\"limit_offset\" /> -->\r	</select>\r\r");
		fw.write("	<update id=\""+table_name+"_update\" parameterClass=\"com.sing.ren.pojo."+turnUpcase1(table_name)+"\">\r");
		fw.write("		<![CDATA[\r");
		fw.write("			UPDATE "+upcase(table_name)+" SET\r");
		fw.write("			"+arry[0]+" = #"+turnUpcase(arry[0])+"#\r		]]>\r");
		for(int i=0;i<arry.length;i++){
			fw.write("		<isNotEmpty prepend=\",\" property=\""+turnUpcase(arry[i])+"\">\r			<![CDATA[ "+arry[i]+" = #"+turnUpcase(arry[i])+"#  ]]>\r		</isNotEmpty>\r");
		}
		fw.write("		<![CDATA[\r			WHERE\r");
		fw.write("				"+arry[0]+" = #"+turnUpcase(arry[0])+"#\r		]]>\r	</update>\r\r");
		fw.write("	<delete id=\""+table_name+"_delete\" parameterClass=\"com.sing.ren.pojo."+turnUpcase1(table_name)+"\">\r		<![CDATA[\r			DELETE FROM "+upcase(table_name)+" WHERE "+arry[0]+" = #"+turnUpcase(arry[0])+"#\r		]]>\r	</delete>\r</sqlMap>");
		fw.write("");
		fw.flush();
        fw.close();
        //======= 創建 POJO =====================
        fw = new FileWriter("E:\\workspace\\workspace_ren-sing\\ren-sing\\src\\main\\java\\com\\sing\\ren\\pojo\\"+turnUpcase1(table_name)+".java");
        fw.write("package com.sing.ren.pojo;\r\r");
        fw.write("public class "+turnUpcase1(table_name)+" extends BasePOJO{\r\r");
        for(int i=0;i<arry2.length;i++){
        	if(!arry[i].equals("updater")&&!arry[i].equals("update_time")){
				if(arry2[i].equals("int")){
					fw.write("	private Integer "+turnUpcase(arry[i])+";\r\r");
				}else if(arry2[i].equals("varchar")){
					fw.write("	private String "+turnUpcase(arry[i])+";\r\r");
				}
        	}	
		}
        for(int i=0;i<arry.length;i++){
        	//判斷不為更新者
			if(!arry[i].equals("updater")&&!arry[i].equals("update_time")){   
				if(arry2[i].equals("int")){
					fw.write("	public Integer get"+turnUpcase1(arry[i])+"() {\r		return "+turnUpcase(arry[i])+";\r	}\r\r");
					fw.write("	public void set"+turnUpcase1(arry[i])+"(Integer "+turnUpcase(arry[i])+") {\r		this."+turnUpcase(arry[i])+" = "+turnUpcase(arry[i])+";\r	}\r\r");
				}else if(arry2[i].equals("varchar")){
					fw.write("	public String get"+turnUpcase1(arry[i])+"() {\r		return "+turnUpcase(arry[i])+";\r	}\r\r");
					fw.write("	public void set"+turnUpcase1(arry[i])+"(String "+turnUpcase(arry[i])+") {\r		this."+turnUpcase(arry[i])+" = "+turnUpcase(arry[i])+";\r	}\r\r");
				}
			}
		}
        fw.write("}");
        fw.flush();
        fw.close();
        
        //=============創建 DAO ======================  
        fw = new FileWriter("E:\\workspace\\workspace_ren-sing\\ren-sing\\src\\main\\java\\com\\sing\\ren\\dao\\table\\"+turnUpcase1(table_name)+"DAO.java");
        fw.write("package com.sing.ren.dao.table;\r\r");
        fw.write("import java.util.List;\r");
        fw.write("import java.util.Map;\r\r");
        fw.write("import org.apache.commons.collections.MapUtils;\r");
        fw.write("import org.springframework.stereotype.Component;\r\r");
        fw.write("import com.sing.ren.dao.BaseDao;\r");
        fw.write("import com.sing.ren.pojo."+turnUpcase1(table_name)+";\r\r");
        fw.write("@Component\rpublic class "+turnUpcase1(table_name)+"DAO extends BaseDao {\r\r");
        fw.write("	@SuppressWarnings(\"deprecation\")\r");
        fw.write("	public List<"+turnUpcase1(table_name)+"> query("+turnUpcase1(table_name)+" dto) throws Exception {\r");
        fw.write("		logger.debug(\""+turnUpcase1(table_name)+"DAO query"+turnUpcase1(table_name)+"\");\r");
        fw.write("		return getSqlMapClientTemplate().queryForList(nsForTableStatements(\""+table_name+"_query\"), dto);\r	}\r");
        fw.write("	@SuppressWarnings(\"deprecation\")\r");
        fw.write("	public void insert("+turnUpcase1(table_name)+" dto) throws Exception {\r");
        fw.write("		logger.debug(\""+turnUpcase1(table_name)+"DAO insert"+turnUpcase1(table_name)+"\");\r");
        fw.write("		getSqlMapClientTemplate().insert( nsForTableStatements(\""+table_name+"_insert\"), dto);\r	}\r");
        fw.write("	@SuppressWarnings(\"deprecation\")\r");
        fw.write("	public void update("+turnUpcase1(table_name)+" dto) throws Exception {\r");
        fw.write("		logger.debug(\""+turnUpcase1(table_name)+"DAO update"+turnUpcase1(table_name)+"\");\r");
        fw.write("		getSqlMapClientTemplate().queryForList(nsForTableStatements(\""+table_name+"_update\"), dto);\r	}\r");
        fw.write("	@SuppressWarnings(\"deprecation\")\r");
        fw.write("	public void delete("+turnUpcase1(table_name)+" dto) throws Exception {\r");
        fw.write("		logger.debug(\""+turnUpcase1(table_name)+"DAO "+turnUpcase1(table_name)+"Detail\");\r");
        fw.write("		getSqlMapClientTemplate().queryForList(nsForTableStatements(\""+table_name+"_delete\"), dto);\r	}\r\r");
        fw.write("	public "+turnUpcase1(table_name)+" mapToObject(Map<String, Object> map) throws Exception {\r");
        fw.write("		"+turnUpcase1(table_name)+" obj = new "+turnUpcase1(table_name)+"();\r\r");
        for(int i=0;i<arry.length;i++){
			if(arry2[i].equals("int")){
				fw.write("		obj.set"+turnUpcase1(arry[i])+"( MapUtils.getInteger(map, \""+arry[i]+"\") );\r");
			}else if(arry2[i].equals("varchar")){
				fw.write("		obj.set"+turnUpcase1(arry[i])+"( MapUtils.getString(map, \""+arry[i]+"\", \"\") );\r");
			}
		} 
        fw.write("\r	return obj;\r	}\r}");
        fw.flush();
        fw.close();
        
        //========== 寫入sqlmapConfig.xml ===========
        //讀出檔案
        FileReader fr = new FileReader("E:\\workspace\\workspace_ren-sing\\ren-sing\\src\\main\\resources\\sqlmap\\sqlmapConfig.xml");
        BufferedReader br =new BufferedReader(fr);
        String xml="";
        while (br.ready()) {
        	xml=xml+br.readLine()+"\r";
        }
        fr.close();
        //如果沒比對到重覆，就寫入mapping
        if(xml.indexOf(turnUpcase(table_name))==-1){
        	xml=xml.substring(0,xml.lastIndexOf("xml\" />")+7)+"\r	<sqlMap resource=\"sqlmap/by_table/"+turnUpcase(table_name)+".xml\" />"+xml.substring(xml.lastIndexOf("xml\" />")+7,xml.length());
        	fw = new FileWriter("E:\\workspace\\workspace_ren-sing\\ren-sing\\src\\main\\resources\\sqlmap\\sqlmapConfig.xml");
	        fw.write(xml);
	        fw.flush();
	        fw.close();
        }
	
	
	
	System.out.println("完成");
	}
	
	
	
	
	
	//======================== 方法大權===============================
	
	public static String turnLowcase(String s) {                   //大寫拿掉加底線     
		 String [] str = new String[s.length()];
		 for(int i=0;i<s.length();i++){                          //字串塞入陣列
			   str[i]=s.substring(i,i+1);
			  }
		 s="";
		 for(int i=0;i<str.length;i++){
			 if(str[i]!=str[i].toLowerCase()){
		//		 System.out.println(str[i]);
				 s=s+"_"+str[i].toLowerCase();
			 }else{
				 s=s+str[i] ;
			 }
		 }
		return s;
	}
	
	public static String turnUpcase(String s) {                 //底線轉大寫
		String [] str = new String[s.length()];
		  for(int i=0;i<s.length();i++){                          //字串塞入陣列
		   str[i]=s.substring(i,i+1);
		  }
		  s="";
		  for(int i=0;i<str.length;i++){   
		
		     if(str[i].equals("_")){               //找出_的下一個字
		      str[i+1]=str[i+1].toUpperCase();  //改大寫
		     }		   
		   s=s+str[i];                                         //陣列內的字組合
		  }
		  s = s.replace("_", "");                          //去掉底線
		return s;
	}
	
	public static String turnUpcase1(String s) {                 //底線轉大寫 (第一個字也大寫)
		String [] str = new String[s.length()];
		  for(int i=0;i<s.length();i++){                          //字串塞入陣列
		   str[i]=s.substring(i,i+1);
		  }
		  s="";
		  for(int i=0;i<str.length;i++){   
		
		     if(str[i].equals("_")){               //找出_的下一個字
		      str[i+1]=str[i+1].toUpperCase();  //改大寫
		     }		   
		   s=s+str[i];                                         //陣列內的字組合
		  }
		  s = s.replace("_", "");                          //去掉底線
		  s=s.substring(0,1).toUpperCase()+s.substring(1,s.length());
		return s;
	}
	
	public static String upcase(String s){
		String [] str = new String[s.length()];
		  for(int i=0;i<s.length();i++){                          //字串塞入陣列
		   str[i]=s.substring(i,i+1);
		  }
		  s="";
		  for(int i=0;i<str.length;i++){   
				
			   str[i]=str[i].toUpperCase();  //改大寫
			   s=s+str[i];                                         //陣列內的字組合
			  } 
		  
		return s;
		
	}
}

