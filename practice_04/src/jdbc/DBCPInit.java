package jdbc;

import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class DBCPInit extends HttpServlet {
       
   @Override
   public void init() throws ServletException {
	   loadJDBCDriver();
   }

	private void loadJDBCDriver() {
		try {
            //내부에서 사용할 JDBC 드라이버를 로딩합니다.
            //oracle에 연결하기 때문에 oracle JDBC 드라이버를 로딩합니다.
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException cnfe) {
			throw new RuntimeException("fail to load JDBC Driver", cnfe);
		}
		
	}
	public void initConnectionPool() {
		try {
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "hr";
			String pw = "hr";
			
			//커넥션 풀이 새로운 커넥션을 생성할 때 커넥션팩토리를 생성합니다.
			//oracle에 연결하므로 oracle JDBC URL, DB계정과 암호를 전달합니다.
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl,user,pw);
			
			//PoolabableConnection을 생성하는 팩토리를 생성합니다.
			//DBCP는 커넥션 풀에 커넥션을 보관할 때 PoolableConnection을 사용합니다.
			//이 클래스는 내부적으로 실제 커넥션을 담고 있으며, 커넥션 풀을 관리하는데 필요한 기능을 추가로 제공합니다.
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			
			//커넥션이 유효한지 여부를 검사할 때 사용할 쿼리를 지정합니다.
			poolableConnFactory.setValidationQuery("select 1");
			
			//커넥션 풀의 설정 정보를 생성합니다. 
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			//유휴 커넥션 검사 주기
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5L);
			//풀에 보관중인 커네션의 유효여부를 검사할지 선택
			poolConfig.setTestWhileIdle(true);
			//커넥션의 최소 개수
			poolConfig.setMinIdle(4);
			//커넥션의 최대 개수
			poolConfig.setMaxTotal(50);
			
			//커넥션 풀을 생성합니다. 생성자는 PoolableConnection을 생성할 때 사용할 팩토리와 커넥션풀을 파라미터로 전달받습니다.
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory,poolConfig);
			//PoolableConnectionFactory에도 위에서 생성한 커넥션 풀을 등록합니다.
			poolableConnFactory.setPool(connectionPool);
			
			//커넥션 풀을 제공하는 JDBC 드라이버를 등록합니다.
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			//커넥션 풀 드라이버에 생성한 커넥션 풀을 등록합니다. "practice_04"를 커넥션 풀 이름으로 주었으므로,
			//JDBC URL은 "jdbc:apache:commons:dbcp:practice_04"입니다.
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool("practice_04", connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
