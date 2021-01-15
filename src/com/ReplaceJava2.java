package com;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.dao.DAOFactory;
import com.hzjc.hz2004.po.PoHJXX_HXXB;

public class ReplaceJava2 {
	public static void main(String argc[]) throws Exception{
		//ReplaceJava2.replaceMatcher("            PoZJXX_JMSFZXXB poSfzxxb = zjxx_jmsfzxxbDAO.insertZJXX_JMSFZXXB(");
		String path = "D:/workspace/hz2004qxgl2/src/com/hzjc/hz2004/service/impl/ZwServiceImpl.java";
		File file = new File(path);
		if(file.isDirectory()){
			File[] fiels = file.listFiles();
			for(File f:fiels){
				if(f.isFile()){
					make(f);
				}else{
					makedir(f);
				}
			}
		}else{
			make(file);
		}
	}
	
	public static void makedir(File file) throws Exception{
		File[] fiels = file.listFiles();
		for(File f:fiels){
			if(f.isFile()){
				make(f);
			}else{
				makedir(f);
			}
		}
	}
	
	public static void make(File file) throws Exception{
		PrintStream fout = new PrintStream(new FileOutputStream(file.getParent() + "/bak.java"));
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line = null;
		while( (line = reader.readLine())!=null){
			if(line.equals("import com.hzjc.wsstruts.service.impl.BaseService;")){
				continue;
			}
			if(line.equals("import com.hzjc.wsstruts.service.impl.BaseService"))
				continue;
			if(line.equals("import net.sf.hibernate.*;"))
				continue;
			if(line.equals("import com.hzjc.hz2004.servlet.bean.SimpleJson;"))
				continue;
			if(line.trim().equals("this.beginTransaction();") || line.trim().equals("beginTransaction();"))
				continue;
			if(line.trim().equals("this.commitTransaction();") || line.trim().equals("commitTransaction();"))
				continue;
			if(line.trim().equals("this.rollbackTransaction();") || line.trim().equals("rollbackTransaction();"))
				continue;
			
			//HJLS_HJYWLSBDAO hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
			if(line.indexOf("DAOFactory.")>=0){
				String daoname = line.trim().split(" ")[0];
				int seek = line.indexOf(daoname);
				String str = line.substring(0,seek) + "PojoInfo " + line.substring(seek + daoname.length());
				line = str;
			}
			
			//hjxx_hxxbDAO.findHJXX_HXXBById(voQhbgxx[i].
            //getHhnbid());
			line = replaceMatcher(line);
			if(line==null){
				continue;
			}
			
			fout.println(line);
			//System.out.println(line);
		}
		
		fout.close();
		reader.close();
	}

	public static String replaceMatcher(String str) {
		str = str.replaceAll("HzZjSb", "PoHZ_ZJ_SB");
		
		String bakstr = str.toUpperCase();
		
	    // 编译正则表达式
		//PoHJXX_HXXB poHxx = hjxx_hxxbDAO.findHJXX_HXXBById(voQhbgxx[i].
		if(bakstr.indexOf("DAO.FIND")>0 && bakstr.indexOf("BYID(")>0){
			try{
				String top = str.split("=")[0].trim();
				String classname = "";
				if(top.trim().startsWith("Po") && top.split(" ").length>1){
					classname = top.split(" ")[0];
				}
				
				String s1 =  str.split("=")[0] +" = super.get(" + classname + ".class," ;
				int seek = str.indexOf("ById(");
				if(seek>0){
					s1 = s1 + str.substring(seek+5);
				}else{
					System.err.print(str);
				}
				
				System.out.println(str + "===>" + s1);
				
				return s1;
			}catch(Exception e){
				e.printStackTrace();
				return str;
			}
		}
		
		//hjyw_qczxxxbDAO.updateHJYW_QCZXXXB(poQczxxx);
		if(bakstr.indexOf("DAO.UPDATE")>0){
			int seek = bakstr.indexOf("DAO.UPDATE");
			int seek2 = str.indexOf("(", seek);
			
			String s1 = "super.update" + str.substring(seek2);
			String top = str.substring(0, seek);
			int seek3 = -1;
			
			seek3 = top.indexOf("=");
			if(seek3>0){
				s1 = top.substring(0, seek3+1) + s1;
			}else{
				seek3 = top.indexOf("(");
				if(seek3>0){
					s1 = top.substring(0, seek3+1) + s1;
				}
			}
			
			seek3 = top.lastIndexOf(" ");
			if(seek3>0){
				s1 = top.substring(0, seek3) + s1;
			}
			System.out.println(str + "===>" + s1);
			
			return s1;
		}
		
		//List hzxxList = hjxx_czrkjbxxbDAO.findAllHJXX_CZRKJBXXBs(strHQL);
		if(bakstr.indexOf("DAO.FINDALL")>0){
			int seek = bakstr.indexOf("DAO.FINDALL");
			int seek2 = str.indexOf("(", seek);
			
			String s1 = "super.findAllByHQL" + str.substring(seek2);
			String top = str.substring(0, seek);
			int seek3 = -1;
			
			seek3 = top.lastIndexOf(" ");
			if(seek3>0){
				s1 = top.substring(0, seek3) + s1;
			}else{
				seek3 = top.indexOf("=");
				if(seek3>0){
					s1 = top.substring(0, seek3) + s1;
				}
			}
			System.out.println(str + "===>" + s1);
			
			return s1;
		}
		
		if(bakstr.indexOf("DAO.FINDONEPAGE")>0){
			int seek = bakstr.indexOf("DAO.FINDONEPAGE");
			int seek2 = str.indexOf("(", seek);
			
			String s1 = "super.getPageRecords" + str.substring(seek2);
			String top = str.substring(0, seek);
			int seek3 = top.lastIndexOf(" ");
			if(seek3>0){
				s1 = top.substring(0, seek3) + s1;
			}else{
				seek3 = top.indexOf("=");
				if(seek3>0){
					s1 = top.substring(0, seek3) + s1;
				}
			}
			System.out.println(str + "===>" + s1);
			
			return s1;
		}
		
		//           PoZJXX_JMSFZXXB poSfzxxb = zjxx_jmsfzxxbDAO.insertZJXX_JMSFZXXB(
		if(bakstr.indexOf("DAO.INSERT")>0){
			//找到关键字
			int seek = bakstr.indexOf("DAO.INSERT");
			//往后找，找到一个(
			int seek1 = str.indexOf("(", seek);
			//往前找，找到一个空格或者等号
			int seek2 = str.lastIndexOf("=", seek);
			
			String top = "";
			if(seek2<0){
				seek2 =  str.lastIndexOf(" ", seek);
				top = str.substring(0,seek2+1);
			}else{
				top = str.substring(0,seek2+1);
			}
			
			String s1 = null;
			if(top.trim().split(" ").length>=2){
				s1 = top + "(" + top.trim().split(" ")[0] + ")super.create" + str.substring(seek1);
			}else{
				s1 = top + "super.create" + str.substring(seek1);
			}
		
			System.out.println(str + "===>" + s1);
			
			return s1;
		}
		
		//voQueryResult.setRecordcount(hjyw_hcybdxxbDAO.getCount(
		if(bakstr.indexOf("DAO.GETCOUNT(")>0){
			int index = bakstr.indexOf("DAO.GETCOUNT(");
			int seek = str.lastIndexOf("(", index);
			String s1 = str.substring(0,seek+1) + "super.getCount(" + str.substring(index + "DAO.getCount(".length());
			if(str.indexOf("=")>0 && str.indexOf("=")<index){
				s1 = str.split("=")[0]  + " = " + s1;
			}
			System.out.println(str + "===>" + s1);
			return s1;
		}
	    
		if(str.indexOf("this.getUserInfo\\(\\)")>=0){
			str = str.replaceAll("this.getUserInfo\\(\\)", "super.getUser\\(\\)");
		}
		
		if(str.indexOf("_log.info")>=0){
			int seek = str.indexOf("_log.info");
			str = str.substring(0,seek) + "//" + str.substring(seek);
		}
		
	    return str;
	}

}
