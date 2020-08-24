package unina.vpacchiano.rest.multisala.thesystem;

public class DBConstants {
	public final static String DB_USER = "root";
	public final static String DB_PASSWORD = "vittoria94";
	public final static String SERVER_NAME = "localhost";
	public final static String PORT_NUMBER = "3306";
	public final static String DB_NAME = "Cinema";
	public final static String URLTIME = "jdbc:mysql://"+DBConstants.SERVER_NAME+":"+DBConstants.PORT_NUMBER+"?serverTimezone=UTC";
	public final static String URL = "jdbc:mysql://"+DBConstants.SERVER_NAME+":"+DBConstants.PORT_NUMBER+"/"+DBConstants.DB_NAME+"?serverTimezone=UTC";

	public final static String NAME_TABLE_SALE = "Sale";
	public final static String NAME_TABLE_FILM = "Film";
	public final static String NAME_TABLE_PROGRAMMAZIONE = "Programmazione";
	public final static String NAME_TABLE_UTENTI = "Utenti";
	public final static String NAME_TABLE_PRENOTAZIONI = "Prenotazioni";
	public final static String NAME_TABLE_CHIAVI = "Chiavi";
	
	public final static String CREATE_DATABASE = "CREATE DATABASE "+DBConstants.DB_NAME;
	public final static String CREATE_TABLE_SALE =	"CREATE TABLE `"+DBConstants.NAME_TABLE_SALE+"` ("+
													"`NOME` VARCHAR(50) NOT NULL,"+
													"`CAPIENZA` INT(11) NOT NULL,"+
													"PRIMARY KEY (`NOME`)"+
													")";
	
	public final static String CREATE_TABLE_FILM = "CREATE TABLE `"+DBConstants.NAME_TABLE_FILM+"` ("+
													"`COD_FILM` VARCHAR(50) NOT NULL,"+
													"`NOME` VARCHAR(50) NOT NULL,"+
													"`REGISTA` VARCHAR(50) NOT NULL,"+
													"`ANNOUSCITA` INT(11) NOT NULL,"+
													"`GENERE` VARCHAR(50) NOT NULL,"+
													"PRIMARY KEY (`COD_FILM`)"+
													")";
	
	public final static String CREATE_TABLE_PROGRAMMAZIONE = "CREATE TABLE `"+DBConstants.NAME_TABLE_PROGRAMMAZIONE+"` ("+
															 "`COD_PROGRAMMAZIONE` VARCHAR(50) NOT NULL,"+
															 "`NOME_SALA` VARCHAR(50) NOT NULL,"+
															 "`COD_FILM` VARCHAR(50) NOT NULL,"+
															 "`DATA` DATE NOT NULL,"+
															 "`ORA` CHAR(5) NOT NULL,"+
															 "`POSTI_PRENOTATI` INT(11) NOT NULL,"+
															 "PRIMARY KEY (`COD_PROGRAMMAZIONE`),"+
															 "UNIQUE (`NOME_SALA`, `DATA`, `ORA`),"+
															 "FOREIGN KEY (`COD_FILM`) REFERENCES `Film` (`COD_FILM`) ON UPDATE CASCADE ON DELETE CASCADE,"+
															 "FOREIGN KEY (`NOME_SALA`) REFERENCES `Sale` (`NOME`) ON UPDATE CASCADE ON DELETE CASCADE"+
															 ")";
	
	public final static String CREATE_TABLE_UTENTI = "CREATE TABLE `"+DBConstants.NAME_TABLE_UTENTI+"` ("+
													 "`NOME_UTENTE` VARCHAR(50) NOT NULL,"+
													 "`NOME` VARCHAR(50) NOT NULL,"+
													 "`COGNOME` VARCHAR(50) NOT NULL,"+
													 "`EMAIL` VARCHAR(50) NOT NULL,"+
													 "`PASSWORD` VARCHAR(50) NOT NULL,"+
													 "`NASCITA` DATE NOT NULL,"+
													 "`ADMIN` BIT(1) NOT NULL,"+
													 "PRIMARY KEY (`NOME_UTENTE`),"+
													 "UNIQUE (`EMAIL`)"+
													 ")";
	
	public final static String CREATE_TABLE_PRENOTAZIONI = "CREATE TABLE `"+DBConstants.NAME_TABLE_PRENOTAZIONI+"` ("+
														   "`COD_PRENOTAZIONE` VARCHAR(50) NOT NULL ,"+
														   "`NOME_UTENTE` VARCHAR(50) NOT NULL,"+
														   "`COD_PROGRAMMAZIONE` VARCHAR(50) NOT NULL,"+
														   "`POSTI_PRENOTATI` INT NOT NULL,"+
														   "PRIMARY KEY (`COD_PRENOTAZIONE`),"+
														   "FOREIGN KEY (`COD_PROGRAMMAZIONE`) REFERENCES `Programmazione` (`COD_PROGRAMMAZIONE`) ON UPDATE CASCADE ON DELETE NO ACTION,"+
														   "FOREIGN KEY (`NOME_UTENTE`) REFERENCES `Utenti` (`NOME_UTENTE`) ON UPDATE CASCADE ON DELETE NO ACTION"+
														   ")"; 
	
	public final static String CREATE_TABLE_CHIAVI = "CREATE TABLE `"+DBConstants.NAME_TABLE_CHIAVI+"` ("+
			"`COD_UTENTE` VARCHAR(50) NOT NULL,"+
			"`CHIAVE_UTENTE` VARCHAR(50) NOT NULL,"+
			"PRIMARY KEY (`COD_UTENTE`),"+
			"FOREIGN KEY (`COD_UTENTE`) REFERENCES `Utenti` (`NOME_UTENTE`) ON UPDATE CASCADE ON DELETE NO ACTION"+
			")";


	
	
}
