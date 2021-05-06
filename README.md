This project is a school project for OTP1 course in Metropolia UAS.
The purpose of the course was to teach Scrum based working methods while developing a software which uses database.

The project team consisted of 5 IT engineering students in Metropolia UAS.
The subject chosen for the software was a game-environment software where users can play a variety of games to help them through lockdown.
The name for the software is Peliympäristö which is finnish for a game-environment.

********************************************

The software is made with Java using JDK version 1.8.0_261. GUI is built with JavaFX components.
The software connects to a MySQL database made with MariaDB. The database is located in Metropolia UAS educloud server. To connect to the database a Metropolia VPN connection is required. A log in to shell.metropolia.fi is also required. This can be done through PuTTY using following instructions:

PuTTY: shell.metropolia.fi, port 22
SSH --> Tunnels: 
sourceport: "localhost:2206",
destination: "10.114.32.61:3306"

********************************************

Hibernate ORM -connection tool will also need to know how to connect to the database. This can be done by adding hibernate.cfg.xml document with following lines to peliymparisto/src/main/resources:

<hibernate-configuration>
	<session-factory>
		<!-- Use mySQL database -->
		<property name="hibernate.dialect"> org.hibernate.dialect.MySQL5Dialect</property>

		<!-- Driver, Server, User name, User password -->
		<property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
		<property name="hibernate.connection.url">jdbc:mysql://localhost:2206/gamedatabase</property>
		<property name="hibernate.connection.username">********</property>
		<property name="hibernate.connection.password">********</property>

		<!-- create , create-drop, update or validate -->
		<!-- Dont use in production -->
		<!-- First time create, when database exists then validate -->
		<property name="hbm2ddl.auto">validate</property>

		<!-- when true, show all SQL-commands in stdout -->
		<property name="show_sql">false</property>

		<!-- model DTO- objects, by packaging -->
		<mapping class="model.Player"/>
		<mapping class="model.PlayedGame"/>
	</session-factory>
</hibernate-configuration>

********************************************

Required libraries and files (Add to Java Build Path):

mysql-connector-java-5.1.49-bin-jar
All required libraries for Hibernate (found in Hibernate-release-version.Final -> lib -> required)
JavaFX (for GUI)
JUnit 5 (for testing)
Maven Dependencies (Project must be maven project)

********************************************

SQL commands for generating a copy of the database used can be found in this repository. It is named SQL_commands.sql.

********************************************

Source code for Chat server can be found in this repository. Replace the socket parameters for your own in EchoClient.java and EchoServer.java files (found in peliymparisto/src/main/java/client  and chat_server/server folders). To compile the server code: 
1. Navigate to the root folder of the server repository.
2. Type "javac ./server/RunServer.java" and repeat to all existing files to create .class files.
3. Type "java server/RunServer" to run the server.

********************************************

Jenkins is used for integration and testing. Jenkins can be accessed here: http://10.114.32.61:8080/ (Metropolia VPN and credentials required)

To easy install:
1. Clone this repository to Maven supported IDE.
2. Run Main.java (or View.java)
