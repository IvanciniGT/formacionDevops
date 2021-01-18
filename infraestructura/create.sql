
CREATE LOGIN appDBCDonaPreUser   
    WITH PASSWORD = 'MyPassw0rd+';  
GO  

CREATE USER appDBCDonaPreUser FOR LOGIN appDBCDonaPreUser;  
GO  

EXEC master..sp_addsrvrolemember @loginame = N'appDBCDonaPreUser', @rolename = N'dbcreator'
GO
