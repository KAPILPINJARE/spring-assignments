
@Configuration
public MyJavaConfig
{
	@Bean
	public BankAccountServiceImpl getServiceImpl()
	{
		return new BankAccountServiceImpl(getDaoImpl());
	}
	
	@Bean
	public BankAccountDaoImpl getDaoImpl()
	{
		return new BankAccountDaoImpl(getDbConnection());
	}

	@Bean
	public Connection getDbConnection()
	{
		Dbutil get = new Dbutil();
		return get.getConnection(getDatabaseDetails());
	}

	@bean
	public Properties getDatabaseDetails()
	{
		FileReader reader = null;
		try
		{
			reader = new FileReader("src/dbConfig.properties");
			databaseDetails = new Properties();
			databaseDetails.load(reader);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return databaseDetails;
	}
}