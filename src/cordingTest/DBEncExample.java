package cordingTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class DBEncExample {

	public static void main(String args[]) throws IOException {
		
		String ALGORITHM = "PBEWithMD5AndDES";
		String ENC_PASSWORD = "jasyptKey";
		
//		String dbScheme = "glndb_blc";
//		String dbUser = "glnuser_blc";
//		String dbScheme = "glndb_member";
//		String dbUser = "glnuser_member";
//		String dbScheme = "glndb_batch";
//		String dbUser = "glnuser_batch";
//		String dbScheme = "glndb_gcoin";
//		String dbUser = "glnuser_gcoin";
//		String dbScheme = "glndb_main";
//		String dbUser = "glnuser_main";
//		String dbScheme = "glndb_payment";
//		String dbUser = "glnuser_payment";
//		String dbScheme = "glndb_portal";
//		String dbUser = "glnuser_portal";
		String dbScheme = "glndb_settlement";
		String dbUser = "glnuser_settlement";
		String dbPwd = "Gln1234!";
		
		
//		String dbScheme = "glndb_common";
//		String dbUser = "glnuser_common";
//		String dbScheme = "glndb_syscommon";
//		String dbUser = "glnuser_syscommon";		
		boolean common = true;
		
		
		String masterPubIp = "172.16.13.38";
		String slavePubIp = "172.16.13.38";
		
		String masterPort = "33060";
		String slavePort = "33060";
		
		//�븫�샇�솕 �븷 �궡�슜(master DB)
		String masterDbPubUrl = "jdbc:mysql://"+masterPubIp+":"+masterPort+"/"+dbScheme+"?autoCommit=true&autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT";
		//�븫�샇�솕 �븷 �궡�슜(slave DB)
		String slaveDbPubUrl = "jdbc:mysql://"+slavePubIp+":"+slavePort+"/"+dbScheme+"?autoCommit=true&autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&serverTimezone=GMT";
		
		StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
		//�븣怨좊━利�
		pbeEnc.setAlgorithm(ALGORITHM);
		//�븫�샇�솕 �궎
		pbeEnc.setPassword(ENC_PASSWORD);
		
		//�븫�샇�솕 �븳 �궡�슜(master DB)
		System.out.println("plain(" + masterDbPubUrl + ")");
		System.out.println("");
		System.out.println("===================================================================");
		System.out.println("user:"+dbUser);
		System.out.println("scheme:"+dbScheme);
		System.out.println("masterPubIp:"+masterPubIp+" port:"+masterPort);
		System.out.println("slavePubIp:"+slavePubIp+" port:"+slavePort);
		System.out.println("===================================================================");
		System.out.println("");
		
		
		String prefix = "";
		if(common) {
			if(StringUtils.equals(dbUser, "glnuser_common")) prefix = "common.";
			if(StringUtils.equals(dbUser, "glnuser_syscommon")) prefix = "syscommon.";
		}
		
		StringBuilder DBUrl = new StringBuilder();
		DBUrl.append("################################################################################");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append("# "+dbUser+" master JDBC Definetion");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append("################################################################################");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.driverClassName=com.mysql.cj.jdbc.Driver");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.url=ENC(" + pbeEnc.encrypt(masterDbPubUrl) + ")");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.username=ENC(" + pbeEnc.encrypt(dbUser) + ")");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.password=ENC(" + pbeEnc.encrypt(dbPwd) + ")");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.defaultAutoCommit=true");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.maxTotal=20");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.maxWaitMillis=5000");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.initialSize=0");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.maxIdle=3");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.minIdle=0");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.testWhileIdle=true");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.validationQuery=select 1");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.timeBetweenEvictionRunsMillis=300000");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.numTestsPerEvictionRun=10");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"sqlmap.location=classpath:META-INF/mybatis/mybatis-config.xml");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"db.type=mysql");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(System.getProperty("line.separator"));
		
		
		
		prefix = "slave.";
		if(common) {
			if(StringUtils.equals(dbUser, "glnuser_common")) prefix = "slave.common.";
			if(StringUtils.equals(dbUser, "glnuser_syscommon")) prefix = "slave.syscommon.";
		}
		//slave.syscommon.
		
		DBUrl.append("################################################################################");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append("# "+dbUser+" slave JDBC Definetion");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append("################################################################################");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.driverClassName=com.mysql.cj.jdbc.Driver");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.url=ENC(" + pbeEnc.encrypt(slaveDbPubUrl) + ")");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.username=ENC(" + pbeEnc.encrypt(dbUser) + ")");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.password=ENC(" + pbeEnc.encrypt(dbPwd) + ")");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.defaultReadOnly=true");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.maxTotal=20");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.maxWaitMillis=5000");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.initialSize=0");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.maxIdle=3");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.minIdle=0");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.testWhileIdle=true");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.validationQuery=select 1");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.timeBetweenEvictionRunsMillis=300000");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"jdbc.numTestsPerEvictionRun=10");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"sqlmap.location=classpath:META-INF/mybatis/mybatis-config.xml");
		DBUrl.append(System.getProperty("line.separator"));
		DBUrl.append(prefix+"db.type=mysql");
		DBUrl.append(System.getProperty("line.separator"));
		
		
		
		
		//file write
		String fileName = "D:\\GLN_prj\\db_properties\\jdbc.properties";
		if(common) {
			fileName = "D:\\GLN_prj\\db_properties\\common.jdbc.properties";
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    writer.write(DBUrl.toString());
	    writer.close();
		
		System.out.println(DBUrl.toString());
		

		
		//decrypt
		String url = "JlK6PLrdJkPmnrWCZOd86jnearyFMsJcrpuV13TWOYHUUJU13aJwv5CL39jR1eJRRek453UrYVimAu/vP2AvsiDmg0GESwR7DZFPEFgbgzCVrN8BdthQDyiTEdNzWpQTxwKc7hQRVTSBw3+8KgPrd8s2z55yq5Zyb+fKQxNWRG8+D9jCOH/yOS6Rz/V1Z1+xaV3dNs9mKb6Bj2WufiQ9ha67gAH04h+8L1SuFupM8b2Unh8K4jSLgyRrpFmQWi+A1ceyrc9pKxQ=";
		String dec = pbeEnc.decrypt(url);
		System.out.println("dec:"+dec);
		
	}
	
}
