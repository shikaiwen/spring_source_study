package com.kevin.learning.spring.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.directory.SearchControls;


public class DBSearcher {

	//数据库定义
	static enum DBInfo{
		//127.0.0.1
		DBInfo("localhost","3306","root","root");
		String host;String port;String username;String password;
	    private DBInfo(String host, String port,String username,String password) {
	        this.host = host;
	        this.port = port;
	        this.username=username;
	        this.password=password;
	    }
		public String getPort() { return port;}
		public String getHost() { return host; }
		public String getUsername() { return username;}
		public String getPassword() { return password; }
		
	}
	
	
	static String searchTableName = "USER_INFO".toUpperCase();
	
	static String rootUrl = "jdbc:mysql://localhost:3306";
	static String username = "root";
	static String password = "root";
	
	
	static Connection rootConn = null;
	static DBInfo currentDBInfo = null;
	
	
	public static void showHelpInfo(){
		String str = "使用方法：\n"
				+ "-t:查询某个表名称 (精确查找)\n"
				+ "-tt:查询某个表名称(模糊查找)\n"
				+ "-c:查找列名(精确匹配)\n"
				+ "-cc:查找列名(模糊匹配)\n"
				+ "-db:查询数据库,1:开发环境  2:测试环境 ( 如果不传，则两个都查)\n"
				+ "使用示例：java DBSearcher.jar -t t_user \n"
				+ "\t java DBSearcher.jar -c email\n";
		
		System.out.println(str);
	}
	
	/**
	 * 默认查询两个库
	 * db=1：开发库  db=2：测试库，如不传则查两个
	 * -t ：查询某个表名，精确查找
	 * -tt ：查询某个表名，模糊查找
	 * 
	 * 
	 */
	public static void main(String[] args) throws Throwable {
		
//		showHelpInfo();
//		if(true)return;
		
		List<String> argsList = Arrays.asList(args);
		
		if(argsList.contains("-h") || argsList.contains("-help")){
			showHelpInfo();
		}
		
		try{
			
			DBInfo[] dbInfArr = DBInfo.values();
			
			for(int i=0;i<dbInfArr.length;i++){
				DBInfo info = dbInfArr[i];
				
				if(argsList.contains("-db")){
					
					String dbVal = argsList.get( argsList.indexOf("-db")+1 );
					
					int dbIndex = Integer.parseInt(dbVal);
					
					if(dbIndex != i+1) continue;
				}
				
				currentDBInfo = info;
				String url = "jdbc:mysql://"+info.getHost()+":" + info.getPort();
				Connection conn = getConn(url, info.getUsername(), info.getPassword());
				if(conn == null) return;
				rootConn = conn;
				
				System.out.println("SEARCH ON HOST " + currentDBInfo.getHost() + ",PORT: " + currentDBInfo.getPort());
				
				for(int j=0;j<args.length;j++){
					
					String opt = args[j];
					if("-t".equals(opt)){
						String tbname = args[j+1];
						searchTable(tbname);
					}
					
					if("-tt".equals(opt)){
						String tbname = args[j+1];
						searchTable(tbname, true);
					}
					
					if("-c".equals(opt)){
						String colname = args[j+1];
						searchCol(colname);
					}
					
					if("-cc".equals(opt)){
						String colname = args[j+1];
						searchCol(colname, true);
					}
				}
			}
			
		}catch(Exception e){
			showHelpInfo();
		}
		
	}
	
	
	public void getTable(){
		String tableName = "t_product";
		searchTable(tableName);
	}
	
	
	public void searchCol(){
		searchCol("email");
	}
	
	
	public void getColWithVal(){
		getColWithVal(null,null,"email","googlevsbing@126.com");
	}
	
	public void getColWithVal(String searchDB,String table,String searchCol , String val){
		searchDB = searchDB == null ? null : searchDB.toUpperCase();
		table = table == null ? null : table.toUpperCase();
		
		searchCol = searchCol.toUpperCase();
		
		List<String> dbList = getAllDB(rootUrl+"/information_schema",username,password);
		
		if(searchDB!= null && !dbList.contains(searchDB)) {
			System.out.println(this.rootUrl +" does not contain " + searchDB);
			return;
		}
		
		for(Iterator<String>iter=dbList.iterator();iter.hasNext();){
			
			String dbName = iter.next();
			if(searchDB != null && !dbName.equals(searchDB)){
				//如果传了searchDb，则只查找一个库
				continue;
			}
			
			List<String> tableList = getTablesInDB(dbName);
			
			//对某个库的连接
			Connection conn  = getConn(rootUrl + "/"+dbName, username, password);
			
			for(Iterator<String> tbIter=tableList.iterator();tbIter.hasNext();){
				String tbName = tbIter.next();
				
				if(table != null && !tbName.equals(table)) continue;
				
//				Connection conn = getConn(rootUrl + "/" + dbName, username, password);
				
				List<String> colsList = getColsOfTable(tbName,dbName);
				if( table != null && !colsList.contains(searchCol) ){
					System.out.println(" table " + table + " does not contain col " + searchCol);
					continue;
				}
				if(!colsList.contains(searchCol)) continue;
				
				
				StringBuilder sql = new StringBuilder();
				sql.append(" select * from " + tbName +" where " + searchCol + " = '" + val  + "'");
				
				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql.toString());
//					pstmt.setObject(1, val);
					pstmt.setFetchSize(Integer.MAX_VALUE);
					
					ResultSet rs = pstmt.executeQuery();
					int count = 0;
					ResultSetMetaData rsMeta =   rs.getMetaData();
					int colCount = rsMeta.getColumnCount();
					List<String> rsColList = new ArrayList<String>();
					for(int i=1;i<=colCount;i++){
//						System.out.println(rsMeta.getColumnLabel(1));
						rsColList.add( rsMeta.getColumnLabel(i) );
					}
					
					boolean hasResult = rs.next();
					if(!hasResult) continue;
					
					System.out.println("============FOUND MATCHED VAL IN TABLE " + tbName + " OF DB "+ dbName+" ============");
					System.out.println("SEARCH COL :" + searchCol + " ,SEARCH VAL: " + val);
					do {
						
						for(Iterator<String> rsColIter = rsColList.iterator();rsColIter.hasNext();){
							
							if ( rsColList.size()<count  || count >= 5 ) break;
							String colLabel = rsMeta.getColumnLabel(count+1);
							System.out.println( rsColList.get(count) + " : " + rs.getObject(colLabel));
							count++;
						}
						
					}while(rs.next());
					
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				}
				
			}
		}
		
		
	
	public static void searchCol(String searchCol){
		searchCol(searchCol,true);
	}
	
	//找列
	public static void searchCol(String searchCol,boolean definite){
		
		searchCol = searchCol.toUpperCase();
		
		List<String> dbList = getAllDB(rootUrl,username,password);
		for(Iterator<String>iter=dbList.iterator();iter.hasNext();){
			String dbName = iter.next();
			List<String> tableList = getTablesInDB(dbName);
			
			//对某个库的连接
//			Connection tableConn  = getConn(rootUrl + "/"+dbName, username, password);
			switchToDB(dbName);
			for(Iterator<String> tbIter=tableList.iterator();tbIter.hasNext();){
				String tbName = tbIter.next();
				
//				Connection conn = getConn(rootUrl + "/" + dbName, username, password);
				
				List<String> colsList = getColsOfTable(tbName,dbName);
				
				List<String> matchColList = new ArrayList<String>();
				//将逻辑改成字符串的包含
				for(Iterator<String> colIter=colsList.iterator();colIter.hasNext();){
					String col = colIter.next();
					
					if(definite){
						if(col.equals(searchCol)){
							System.out.println("FOUND COLUMN LIKE " + searchCol + 
									","+ col  +" IN TABLE " + tbName + " OF DB " + dbName);
						}
					}else{
						if(col.contains(searchCol)){
							System.out.println("FOUND COLUMN LIKE " + searchCol + 
									","+ col  +" IN TABLE " + tbName + " OF DB " + dbName);
//							matchColList.add(col);
						} 
						
					}
					
					
				}
				
			}
		}
		
	}
	
	// 获取一个表的所有列，这里一定要提供dbName,之前想通过 conn.getCatalog()的方式获取dbname，但发现，switchDB后
	//获取不到catalog，不知道是mysql驱动的bug还是本身就这样
	public static List<String> getColsOfTable(String tableName ,String catalog){
		
		try{
//			String catalog = rootConn.getCatalog();
			String sql = "select COLUMN_NAME from information_schema.COLUMNS where table_name = ? and table_schema = ? ";
			PreparedStatement stmt = rootConn.prepareStatement(sql);
			
			stmt.setString(1, tableName);
			stmt.setString(2, catalog);
			
			ResultSet rs = stmt.executeQuery();
			List<String> colsList = new ArrayList<String>();
			while(rs.next()){
				colsList.add(rs.getString(1));
			}
			return colsList;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}

	
	public static void searchTable(String tableName){
		searchTable(tableName,false);
	}
	
	public static void searchTable(String tableName,boolean definite){
		
		tableName = tableName.toUpperCase();
		List<String> dbList = getAllDB(rootUrl,username,password);
		for(Iterator<String>iter=dbList.iterator();iter.hasNext();){
			String dbName = iter.next();
			List<String> tableList = getTablesInDB(dbName);
			
			for(Iterator<String>tbIter=tableList.iterator();tbIter.hasNext();){
				String tb = tbIter.next();
				
				if(!definite){
					if(tb.equals( tableName )){
						System.out.println(" FOUND TABLE " + tableName + " IN DB " + dbName );
					}
				}else{
					if(tb.toUpperCase().contains(tableName)){
						System.out.println(" FOUND TABLE LIKE " + tableName + ","+ tb +" IN DB " + dbName );
					}
				}
			}
		}
	}
	
	
	public  static List<String> getTablesInDB(String dbName){
		try{
//			Connection conn = getConn(rootUrl + "/" + dbName, username, password);
//			Statement stmt = rootConn.createStatement();
//			stmt.execute("use  " + dbName);
			switchToDB(dbName);
			ResultSet tableResultSet = rootConn.createStatement().executeQuery("show tables");
			List<String> tableList = new ArrayList<String>();
			while(tableResultSet.next()){
				String name = tableResultSet.getString(1);
				tableList.add(name.toUpperCase());
			}
			return tableList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getAllDB(String rootUrl,String username,String password){
		try{
			
			List<String> dbList = new ArrayList<String>();
//			Connection rootConn = getConn(rootUrl, username, password);
			
			ResultSet showDbRs = rootConn.createStatement().executeQuery("show databases");
			
			while(showDbRs.next()){
				dbList.add(  showDbRs.getString(1)  );
			}
			
			DatabaseMetaData metaData = rootConn.getMetaData();
			ResultSet rs = metaData.getCatalogs();
			
			
			//这种方式无论connection是指向rootUrl还是具体的DB都可以获取所有库
//			while(rs.next()){
//				String dbname = rs.getString("TABLE_CAT");
//				dbList.add(dbname);
//			}
			return dbList;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static void switchToDB(String dbName){
		try {
			boolean re = rootConn.createStatement().execute("use " + dbName);
//			System.out.println("siwtch to " + dbName + ", result " + re);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//找表
	public void searchTable(){
		try {
			String rootUrl = "jdbc:mysql://172.16.7.232:33096";
			String username = "root";
			String password = "passwd32";
			
			List<String> dbList = new ArrayList<String>();
			Connection rootConn = getConn(rootUrl, username, password);
			DatabaseMetaData metaData = rootConn.getMetaData();
			ResultSet rs = metaData.getCatalogs();
			while(rs.next()){
				String dbname = rs.getString("TABLE_CAT");
//				System.out.println(dbname);
				dbList.add(dbname);
			}
			for(Iterator<String> iter=dbList.iterator();iter.hasNext();){
				String dbname = iter.next();
				Connection con = getConn(rootUrl + "/" + dbname , username,password);
				Statement stmt = con.createStatement();
				ResultSet tableResultSet = stmt.executeQuery("show tables");
				
				List<String> tableList = new ArrayList<String>();
				while(tableResultSet.next()){
					String name = tableResultSet.getString(1);
					tableList.add(name.toUpperCase());
				}
				
				if(tableList.contains(searchTableName)){
					System.out.println( "found " +  searchTableName + " in " + dbname);
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public  static Connection getConn(String url,String name,String password){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
//		String url = "jdbc:mysql://172.16.7.232:33096";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, name, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://172.16.7.232:33096";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "root", "passwd32");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
