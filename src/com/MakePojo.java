package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

public class MakePojo {
	/**
	 * 自动重载资源文件，以同步修改
	 * @author User
	 *
	 */
    public static class MyResourceBundleControl extends ResourceBundle.Control{
	    	private static MyResourceBundleControl myControl;
	
	    	private MyResourceBundleControl() {
	    	}
	
	    	public synchronized static MyResourceBundleControl getControl() {
	    		if (myControl == null) {
	    			myControl = new MyResourceBundleControl();
	    		}
	    		return myControl;
	    	}
	
	    	/**
	    	 * 检查间隔(对符合条件的对象立即更新，单位毫秒)
	    	 */
	    	public long getTimeToLive(String baseName, Locale locale) {
	    		return 0;
	    	}
	
	    	/**
	    	 * 是否需要重新加载的判断函数 overriding方法
	    	 */
	    	public boolean needsReload(String baseName, Locale locale, String format, ClassLoader loader, ResourceBundle bundle,
	    			long loadTime) {
	    		boolean needsReload = true;
	    		return needsReload;
	    	}
    }
    
	private static Map<String,String> hSysteConfig = new HashMap<String,String>();
	static{
		synchronized(hSysteConfig){
			hSysteConfig.clear();
			
			// 将配置文件缓存，MyResourceBundleControl指定当下次读取getBundle时在指定的时间间隔内需要重新加载，否则使用缓存
			PropertyResourceBundle prb = (PropertyResourceBundle)PropertyResourceBundle.getBundle(
					"conf/makepojo",MyResourceBundleControl.getControl());
			
			for (Enumeration<String> e = prb.getKeys(); e.hasMoreElements();) {
				String key = e.nextElement();
				try{
					String value = new String(prb.getString(key).getBytes("ISO-8859-1"),"UTF-8");
	
					//if(value.indexOf("#")>0)
					//	value = value.substring(0,value.indexOf("#"));
					
					hSysteConfig.put(key, value);
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
	
	static Map classType = new HashMap();
	static Set keyWord = new HashSet();
	static{
		keyWord.add("INITIAL");
	}
	
	static{
		classType.put("VARCHAR", new ClassesType("String","","java.lang.String",true));
		classType.put("VARCHAR2", new ClassesType("String","","java.lang.String",true));
		classType.put("NCHAR", new ClassesType("String","","java.lang.String",true));
		classType.put("NVARCHAR2", new ClassesType("String","","java.lang.String",true));
		classType.put("NVARCHAR", new ClassesType("String","","java.lang.String",true));
		classType.put("INTEGER", new ClassesType("Integer","","java.lang.Integer",true));
		classType.put("CHAR", new ClassesType("String","","java.lang.String",true));
		classType.put("CHAR2", new ClassesType("String","","java.lang.String",true));
		classType.put("DATE", new ClassesType("Timestamp","java.sql.Timestamp","java.sql.Timestamp",true));
		classType.put("LONG", new ClassesType("Long","","java.lang.Long",true));
		classType.put("DOUBLE", new ClassesType("Double","","java.lang.Double",true));
		classType.put("FLOAT", new ClassesType("Double","","java.lang.Double",true));
		classType.put("RAW", new ClassesType("String","","java.lang.String",true));
		classType.put("LONG RAW", new ClassesType("byte[]","","java.lang.String",true));
		classType.put("TIMESTAMP", new ClassesType("Date","java.util.Date","java.util.Date",true));
		classType.put("CLOB", new ClassesType("String","","java.lang.String",false));
		classType.put("BLOB", new ClassesType("byte[]","","",false));
	}
	
	static public String savePath="c:/";
	static public String package_name = "com.nlk.netlink.entity";
	static public String interface_name = "java.io.Serializable";
	static public String parent_name = "";
	static public String schema="ZDRYGK_NB";
	static public String dbconnection = "jdbc:oracle:thin:@192.168.2.100:1521:ORCL";
	static public String uid = "wtx_sjtc_summ";
	static public String pwd = "wtx_sjtc_summ";
	static public String driver = "oracle.jdbc.driver.OracleDriver";
	static public String parentname = "BaseEntity";
	
	static public List<String> importPackage = new ArrayList<String>();
	static{
		importPackage.add("javax.persistence.GenerationType");
		importPackage.add("javax.persistence.SequenceGenerator");
		importPackage.add("javax.persistence.Entity");
		importPackage.add("javax.persistence.GeneratedValue");
		importPackage.add("javax.persistence.Id");
		importPackage.add("javax.persistence.Table");
		importPackage.add("org.hibernate.annotations.DynamicInsert");
		importPackage.add("org.hibernate.annotations.DynamicUpdate");
		importPackage.add("org.hibernate.annotations.GenericGenerator");
	}
	
	//<String,List<String>>
	static public Map keyData = new HashMap();
	//<String,String>
	static public Map<String,String> tableCommons = new HashMap<String,String>();	
	//字段注释，KEY=表名＋字段名<String,String>
	static public Map mComments = new HashMap();
	//<String,Vector<Vector<Object>>>: "字段名","数据类型","长度","精度","空值","主键","默认值"
	static public Map mData = new HashMap();
	
	static public String error = "";
	
	public static void main(String[] args) throws Exception{
		Class.forName(hSysteConfig.get("jdbc.driverClass"));
		
		Set<String> tablesSet = new HashSet<String>();
		String str = hSysteConfig.get("tables");
		if(str==null || str.trim().equals("")){
			String[] tables = str.split(",");
			for(String table:tables){
				if(table==null || table.trim().equals(""))
					continue;
				
				tablesSet.add(table.trim().toUpperCase());
			}
		}
		
		savePath = hSysteConfig.get("savePath");
		package_name = hSysteConfig.get("package_name");
		interface_name = hSysteConfig.get("interface_name");
		parentname =  hSysteConfig.get("parentname");
		interface_name = hSysteConfig.get("interface_name");
		parent_name = hSysteConfig.get("parent_name");
		schema = hSysteConfig.get("schema");
		dbconnection =hSysteConfig.get("jdbc.url");
		uid = hSysteConfig.get("jdbc.user");
		pwd = hSysteConfig.get("jdbc.password");
		
		List importPackage = new ArrayList();
		if(schema==null || schema.trim().equals("")){
			schema = uid.toUpperCase();
		}
		
		schema = schema.trim().toUpperCase();
		System.out.print("连接数据库...");
		Connection conn = DriverManager.getConnection(dbconnection, uid, pwd);
		System.out.println("已连接");
		
		//分析数据库
		DatabaseMetaData dm = conn.getMetaData();
		
		String msg = "";
		
		System.out.println("分析数据库...");
		ResultSet res = dm.getTables(null, schema,  null, new String[] { "TABLE" });
		while (res.next()) {
			String table = res.getString("TABLE_NAME").toUpperCase();
			String tableType = res.getString("TABLE_TYPE");
			if(tableType.equals("VIEW")){
					System.out.println("特殊视图：" + table);
			//		continue;
			}
			
			//过滤表
			if(tablesSet.size()>0){
				if(!tablesSet.contains(table))
					continue;
			}

			//------------
			System.out.println("分析表" + table + "...");
			
			ResultSet res2 = dm.getPrimaryKeys(null,schema, table);
			List keylist = (List)keyData.get(table);
			if(keylist==null){
					keylist=new ArrayList();
					keyData.put(table, keylist);
			}
			
			while(res2.next()){
				//String s1 =res2.getString("TABLE_NAME");
				String s2=res2.getString("COLUMN_NAME").toLowerCase();
				keylist.add(s2);
			}
				
			if(keylist.size()==0)
				if(msg.equals("")) msg=table;
				else msg += "\r\n" + table;
					
			res2.close();
					
			res2 = dm.getColumns(null,schema, table,"%");
		    while(res2.next()){			            	
		       	String columnName = res2.getString("COLUMN_NAME").toLowerCase();
		       	String columnType = res2.getString("TYPE_NAME");
		       	String datasize = res2.getString("COLUMN_SIZE");
		       	String digits = res2.getString("DECIMAL_DIGITS");
		       	String nullable = res2.getString("NULLABLE");
		       	String column_def = res2.getString("COLUMN_DEF");
		        
		       	int kh = columnType.indexOf("(");
		       	if(kh>0) columnType = columnType.substring(0,kh);
		       	
		       	if(column_def==null) column_def="";
		       	if(digits==null) digits="";
		       	if(nullable==null) nullable="";
		       	if(datasize==null) datasize="";
		       	
		       	column_def=column_def.trim();
		       	if(column_def.equalsIgnoreCase("null")) column_def="";
		         
		       	column_def = "";
		       	
		        //"表名","字段名","数据类型","长度","精度","空值","主键"
		        Vector<Object> v = new Vector<Object>();
            	v.add(columnName);
            	v.add(columnType);
            	v.add(datasize);
            	v.add(digits);

            	if(nullable.equals("1"))
            		v.add(new Boolean(true));
            	else
            		v.add(new Boolean(false));
		            	
            	if(keylist.contains(columnName.toLowerCase()))
            		v.add(new Boolean(true));
            	else
            		v.add(new Boolean(false));
            	
		        //<Vector<Object>>
		        Vector vrow = (Vector)mData.get(table);
		        if(vrow==null){
		        	//<Vector<Object>>
		        	vrow = new Vector();
		        	mData.put(table, vrow);
		        }
		        
		        v.add(column_def);
		        vrow.add(v);
		    }
		    res2.close();
		}
		res.close();
		
		System.out.println("获取注释信息...");	
	    String sql = "select a1.table_name, a1.column_name, substr(nvl(a2.comments, a1.table_name) ,0,80) comments" +
				"	from all_tab_cols a1, all_col_comments a2 " +
				"		where a1.table_name = a2.table_name(+) " +
				"	and a1.owner = a2.owner(+)" +
				" 	and a1.column_name=a2.column_name(+)" +
				" and a1.owner = '" + schema + "'";
	    Statement stmt = conn.createStatement();
	    res = stmt.executeQuery(sql);
	    while(res.next()){
	       	String tablename = res.getString("table_name").toUpperCase(); 
	       	String colname = res.getString("column_name").toLowerCase();
	       	String comments = res.getString("comments");
	       	if(comments!=null && !comments.trim().equals("") && !comments.equals(tablename)){
	       		//comments = new String(comments.getBytes("UTF-8"),"GBK");
	       		mComments.put(tablename + "_" + colname, comments);
	       	}
	    }
	    res.close();
	    
	    sql = "select table_name,table_type,comments from all_tab_comments ";
	    sql += " where owner = '" + schema + "'";
	    res = stmt.executeQuery(sql);
	    while(res.next()){
	    	String tablename = res.getString("table_name").toUpperCase(); 
	    	String comments = res.getString("comments");
	    	if(comments!=null && !comments.trim().equals("")){
	    		//comments = new String(comments.getBytes("UTF-8"),"GBK");
	    		tableCommons.put(tablename, comments);
	    	}
	    }
	    res.close();
	    stmt.close();
	    conn.close();
	    
		File f = new File(savePath);
		if(!f.exists()){
			f.mkdirs();
		}else{
			File[] fs = f.listFiles();
			for(int i=0;i<fs.length;i++)
				fs[i].delete();
		}
		
		Set<String> tables = mData.keySet();
		for(Iterator<String> it=tables.iterator();it.hasNext();){
			String tablename = (String)it.next();
			
			String pojoname = tablename;
			Vector cols=(Vector)mData.get(tablename);
			List pkList = (List)keyData.get(tablename);
			
			makePrjo(pojoname, cols, pkList, tablename, true);
		}
	}

	static public String getGetMethod(String tablename,String columnName,String pname,String javaType){
		return "\tpublic " + javaType 
				+ " get" + pname.substring(0,1).toUpperCase() + pname.substring(1)
				+ "(){\r\n\t\treturn this." + pname + ";\r\n\t}";
	}
	
	static public String getSetMethod(String tablename,String columnName,String pname,String javaType){
		return "\tpublic void set" + pname.substring(0,1).toUpperCase() + pname.substring(1)
				+ "(" + javaType + " " + pname + "){\r\n"
				+ "\t\tthis." +pname + "=" + pname + ";\r\n\t}";
	}
	
	static public void makePrjo(String pojoname,Vector cols,List pkList,String tablename, boolean istable) throws Exception{
		String top = "Po";
		
		System.out.print("创建POJO对象" + top + pojoname + "...");
		
		PrintWriter f=new PrintWriter(new FileOutputStream(savePath + "\\" + top + pojoname + ".java"));
		
		//全参构造函数
		List gzList = new ArrayList();
		
		Set<String> tempImport = new HashSet<String>();
		Vector<Object> vKey = new Vector<Object>();
		Set<String> eKey = new HashSet<String>();
		
		String oooo="";
		Map<String,String> pMap=new HashMap<String,String>();
		
		//部门编码以oooo开头，如果存在ooooxxx，那么就存在xxx的部门id，这里创建一个所有部门id的静态只读属性
		//计算具体POJO对象需要引入的包，并且分离主键和普通字段
		for(int i=0;i<cols.size();i++){
			Vector<?> v = (Vector<?>)cols.get(i);
        	String columnType = v.get(1).toString();
        	
        	//将所有属性名就压入Map	中。
        	String columnName = v.get(0).toString().toUpperCase();
        	pMap.put(columnName,columnName);
        	
        	if(classType.containsKey(columnType)){
        		ClassesType t = (ClassesType)classType.get(columnType);
        		if(t.getImportType()!=null && !t.getImportType().equals(""))
        			tempImport.add(t.getImportType());
        	}
        	
        	Boolean isKey = (Boolean)v.get(5);
        	if(isKey.booleanValue()){
        		vKey.add(v);
        		eKey.add(v.get(0).toString());
        	}
		}
		
    	if(vKey.size()==0){
    		System.err.println(tablename + "没有主键！");
    		return;
    	}
    	
		//编写包头
		if(package_name!=null && !"".equals(package_name)){
			f.println("package " + package_name + ";");
			f.println();
		}
		
		//编写公共import对象
		for(Iterator<String> it=importPackage.iterator();it.hasNext();){
			String imp = it.next().toString();
			f.println("import " + imp + ";");
		}
		
		//编写POJO独立的import对象
		for(Iterator it=tempImport.iterator();it.hasNext();){
			String imp = it.next().toString();
			f.println("import " + imp + ";");
		}
		f.println();
		
		//编写JavaDoc注释
		if(tableCommons.containsKey(tablename)){
			String tableRemark = tableCommons.get(tablename).toString();
			f.println("/**\r\n*" + tableRemark + "\r\n*/");
		}
		
		f.println("@Entity");
		f.println("@Table(name=\"" + tablename + "\" )");
		f.println("@DynamicUpdate(true)");
		f.println("@DynamicInsert(true)");
		if(vKey.size()==1){
			Vector v = (Vector)vKey.get(0);
        	String columnName = v.get(0).toString();
        	String columnType = v.get(1).toString();
        	String datasize = v.get(2).toString();
        	String digits = v.get(3).toString();
        	//Boolean nullable = (Boolean)v.get(4);
        	//Boolean isKey = (Boolean)v.get(5);
        	
        	String javaType = null;
        	if(columnType.equals("NUMBER")){
        		if(digits==null || digits.equals("") || digits.equals("0"))
        			columnType = "LONG";
        		else
        			columnType = "DOUBLE";
        	}
        	
        	ClassesType t = (ClassesType)classType.get(columnType);
        	if(t==null){
        		new Exception("严重错误：数据类型" + columnType + "没有找到定义信息！");
        	}
        	
        	javaType = t.getJavaType();
    		if(javaType.equals("Long")){
    			String name = "SID_" + tablename.toUpperCase();
    			//f.println("@SequenceGenerator(name=\"" + name + "\", sequenceName=\"" + name + "\")");  
    		}
		}
		
		//编写类声明
		f.print("public class " + top + pojoname);
		if(parentname!=null && !"".equals(parentname)) f.print(" extends " + parentname);
		if(interface_name!=null && !"".equals(interface_name)) f.print(" implements " + interface_name);
		
		f.println("{");
		
		f.println("\n\tprivate static final long serialVersionUID = 1L;\r\n");
		
		String methodString = "";
		
		if(vKey.size()==0)
			error += "表" + tablename + "不存在主键;";
		else if(vKey.size()>1)
			error += "表" + tablename + "存在联合主键;";
    	
		for(int i=0;i<vKey.size();i++){
			Vector v = (Vector)vKey.get(i);
        	String columnName = v.get(0).toString();
        	String columnType = v.get(1).toString();
        	String datasize = v.get(2).toString();
        	String digits = v.get(3).toString();
        	//Boolean nullable = (Boolean)v.get(4);
        	//Boolean isKey = (Boolean)v.get(5);
        	
        	String javaType = null;
        	if(columnType.equals("NUMBER")){
        		if(digits==null || digits.equals("") || digits.equals("0"))
        			columnType = "LONG";
        		else
        			columnType = "DOUBLE";
        	}
        	
        	ClassesType t = (ClassesType)classType.get(columnType);
        	if(t==null){
        		new Exception("严重错误：数据类型" + columnType + "没有找到定义信息！");
        	}
        	
        	javaType = t.getJavaType();
        	
            String key = tablename + "_" + columnName;
            if(mComments.containsKey(key)){
            	String commons = mComments.get(key).toString();
            	f.println("\t/*");
            	f.println("\t*" + commons);
            	f.println("\t*/");
            }
            
        	String pname = columnName;

        	
        	if(vKey.size()==1){
        		if(javaType.equals("Long")){
        			//f.println("	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator=\"SID_" + tablename.toUpperCase() + "\")");
        			f.println("	@GeneratedValue(generator = \"generator\") ");
        			f.println("	@GenericGenerator(name = \"generator\", strategy = \"assigned\") ");
        			f.println("	@Id");
        		}else{
        			f.println("	@GenericGenerator(name = \"generator\", strategy = \"uuid.hex\")");
        			f.println("	@Id");
        			f.println("	@GeneratedValue(generator = \"generator\")");
        		}
        	}else{
        		f.println("	@Id");
        	}
        	
        	f.println("\tprivate " + javaType + "\t" + pname + ";");
        	
        	methodString += getGetMethod(tablename,columnName,pname,javaType) + "\r\n\r\n";
        	methodString += getSetMethod(tablename,columnName,pname,javaType) + "\r\n\r\n";
        	
            gzList.add(javaType + "=" + pname);
		}
		
		for(int i=0;i<cols.size();i++){
			Vector v =(Vector)cols.get(i);
        	String columnName = v.get(0).toString();
        	String columnType = v.get(1).toString();
        	String datasize = v.get(2).toString();
        	String digits = v.get(3).toString();
        	Boolean nullable = (Boolean)v.get(4);
        	String defValue = v.get(6).toString();
        	
        	//如果是主键，那么不再重复创建
        	if(eKey.contains(columnName)) continue;
        	
        	//创建默认值
        	String javaType = null;
        	
        	//oracle的LONG,翻译成VARCHAR2
        	if(columnType.equals("LONG")){
        		columnType = "VARCHAR2";
        	}
        	
        	if(columnType.equals("NUMBER")){
        		if(digits==null || digits.equals("") || digits.equals("0")){
        			columnType = "LONG";
        			if(!defValue.equals("")) defValue="new Long(" + defValue + "l)";
        		}else{
        			columnType = "DOUBLE";
        			if(!defValue.equals("")) defValue="new Double(" + defValue + "d)";
        		}
        	}
        	
        	if(columnType.equals("DATE"))
        		if(defValue.trim().equals("SYSDATE")) defValue="new Date()";
        	
        	if(columnType.startsWith("VARCHAR")
        			|| columnType.startsWith("CHAR")
        			|| columnType.startsWith("NCHAR")
        			|| columnType.startsWith("NVARCHAR")){
        		if(!defValue.equals("")){
        			defValue = defValue.replaceAll("\"", "\\\"").trim();
        			if(defValue.startsWith("'")) defValue=defValue.substring(1);
        			if(defValue.endsWith("'")) defValue=defValue.substring(0,defValue.length()-1);
        			defValue = "\"" + defValue + "\"";
        		}
        	}
        	
        	ClassesType t = (ClassesType)classType.get(columnType);
        	if(t==null)
        		throw new Exception("严重错误：数据类型" + columnType + "没有找到定义信息！");
        	        	
        	javaType = t.getJavaType();
        	
        	String pname = columnName;
        	
            String key = tablename + "_" + columnName;
            if(mComments.containsKey(key)){
            	//String commons = mComments.get(key).toString();
            	//f.println("\t/*");
            	//f.println("\t*" + commons);
            	//f.println("\t*/");
            }
            
            if(defValue.equals(""))
            	f.println("\tprivate " + javaType + "\t" + pname + ";");
            else
            	f.println("\tprivate " + javaType + "\t" + pname + "=" + defValue + ";");
            
        	methodString += getGetMethod(tablename,columnName,pname,javaType) + "\r\n\r\n";
        	methodString += getSetMethod(tablename,columnName,pname,javaType) + "\r\n\r\n";
        	
        	gzList.add(javaType + "=" + pname);
		}
		
		//编写空构造方法
		f.println("\r\n\tpublic " + top + pojoname + "(){}");
		f.println();
		
		//构造全构造函数
		/*
		f.print("\r\n\tpublic " + pojoname + "(");
		int icount=0;
		for(int i=0;i<gzList.size();i++){
			String s = gzList.get(i).toString();
			String[] ss = s.split("=");
			String itype = ss[0];
			String iname = ss[1];
			if(icount==0)
				f.print(itype + " " + iname);
			else
				f.print(",\r\n\t\t\t" + itype + " " + iname);
			
			icount ++;
		}
		f.println(")\r\n\t{");

		
		for(int i=0;i<gzList.size();i++){
			String s = gzList.get(i).toString();
			String[] ss = s.split("=");
			String iname = ss[1];
			
			f.println("\t\tthis." + iname + "=" + iname + ";");
		}
		
		f.println("\t}");
		f.println();
		 */
		
		f.print(methodString);
		
		//覆盖hashCode方法
		if(eKey==null){
			System.out.println("err");
		}
		
//		if(pkList==null || pkList.size()==0)
//			;
//		else{
//			String pname = (String)pkList.get(0);
//			f.println("\tpublic int hashCode(){");
//			String s1 = null;
//			for(Object s :pkList){
//				s = getEntityPName(s.toString());
//				f.println("\t\tif(" + s + "==null) return super.hashCode();");
//				if(s1==null)
//					s1 = s.toString() + " + \"\"";
//				else
//					s1 += " + \"_\" + " + s;
//			}
//			f.println("\t\treturn (" + s1 + ").hashCode();");
//			f.println("\t}");
//			
//			//覆盖equals方法
//			f.println("\tpublic boolean equals(Object obj){");
//			f.println("\t\tif(obj==null || !(obj instanceof " + pojoname + ") ) return false;");
//			f.print("\t\treturn ");
//			int i=0;
//			for(Object s:pkList){
//				s = getEntityPName(s.toString());
//				
//				if(i==0)
//					f.print("this." + s + ".equals(((" + pojoname + ")obj)." + s + ")");
//				else
//					f.print(" && this." + s + ".equals(((" + pojoname + ")obj)." + s + ")");			
//				i++;
//			}
//			f.println("\t\t;");
//			
//			f.println("\t}");
//		}
		
		f.print("\tpublic static long getSerialversionuid() {");
		f.print("\t\t	return serialVersionUID;");
		f.print("\t}");
		
		f.print("}");
		f.flush();
		f.close();
		
		System.out.println("完成！");
	}
}


class ClassesType{
	String javaType = "";		//java类型
	String importType = "";		//要引入的包
	String hbmJavaType = "";	//Hibernate对应定义的数据类型
	boolean isLength = true;
	
	public ClassesType(String javaType,String importType,String hbmJavaType,boolean isLength){
		this.importType = importType;
		this.javaType = javaType;
		this.hbmJavaType = hbmJavaType;
		this.isLength = isLength;
	}
	
	public String getJavaType(){return javaType;}
	public String getImportType(){return importType;}
	public String getHbmType(){return hbmJavaType;}
	public boolean isLength(){return isLength;}
}
