package unina.vpacchiano.rest.multisala.thesystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import unina.vpacchiano.rest.multisala.domain.*;


public class GestoreCinema {
	
	private Connection con;
	private static GestoreCinema instance;
	
	public static void main(String [] args) throws SQLException, UtenteSconosciutoException, UtenteDuplicatoException, FilmSconosciutoException, FilmDuplicatoException, SalaSconosciutaException, SalaDuplicataException, ProgrammazioneSconosciutaException, ProgrammazioneDuplicataException, PrenotazioneSconosciutaException, PrenotazioneDuplicataException, PostiTerminatiException, ChiaveSconosciutaException{
		GestoreCinema gest = new GestoreCinema();
		//Utente u = new Utente("Luigino", "Luigi", "Martire", "luigimartire@jj.it", "luigino17", "1994-01-24", true);
		//gest.addUtente(u);
		//gest.addUtente(new Utente("Lallalapalla", "Laura", "Mancini", "lallapallina@alice.it", "lalla44", "1988-08-05", false));
		
		//Utente utente = gest.getUtenteByChiave("56b211bf00faf47d1c3cb7f28104fc8c");
		//System.out.println(utente);
		//Sala s = new Sala("Vittoria", 130);
		//gest.addSala(s);
		
		//Utente f= gest.getUtenteByUsername("Luigino");
		//System.out.println(f+"\n");
		
		//Film fi = new Film("AF021", "La promessa dell assassino", "Hatchak", 2007, "Thriller");
		//gest.addFilm(fi);
		//gest.removeFilm("AF021");
		
		//Film[] ff = gest.getAllFilm();
		//for (Film fr:ff) System.out.println(fr+"\n");
		
		//Film fil = gest.getFilm("AF021"); 
		
		//Sala sa = new Sala("Calliope",521);
		//gest.addSala(sa);
		//gest.removeSala("Calliope");
		
		
		//Programmazione p = new Programmazione ("PR01", "Vittoria", "AF021", "2015-12-01", "11:00", 1);
		//gest.addProgrammazione(p);
		//gest.removeProgrammazione("PR01");
		
		//Programmazione[] prog = gest.getAllProgrammazioni();
		//for (Programmazione pr:prog) System.out.println(pr+"\n");
		
		//Programmazione pr = gest.getProgrammazione("PR03");
		//System.out.println(pr);
		
		//Prenotazione pren = new Prenotazione ("PREN1", "Luigino", "PR01", 27);
		//gest.addPrenotazione(pren);
		//gest.removePrenotazione("PREN1");
		
		//Prenotazione[] pr = gest.getAllPrenotazioni();
		//for (Prenotazione prenn:pr) System.out.println(prenn+"\n");
		
		//pren = gest.getPrenotazione("PREN1");
		//System.out.println(pren+"\n");
		
		
		
	}
	protected GestoreCinema() throws SQLException {
	
		con = DriverManager.getConnection(DBConstants.URLTIME, DBConstants.DB_USER, DBConstants.DB_PASSWORD);
		Statement create = con.createStatement();
		try {
			create.execute(DBConstants.CREATE_DATABASE);
		} catch (SQLException e) {
			System.err.println("Database gi� esistente");
		}
		
		con = DriverManager.getConnection(DBConstants.URL, DBConstants.DB_USER, DBConstants.DB_PASSWORD);
		create = con.createStatement();
		try{
			create.execute(DBConstants.CREATE_TABLE_FILM);	
		} catch (SQLException e) {
			System.err.println("Tabella "+ DBConstants.NAME_TABLE_FILM + " gi� esistente");
		}
		try{
			create.execute(DBConstants.CREATE_TABLE_UTENTI);	
		} catch (SQLException e) {
			System.err.println("Tabella "+ DBConstants.NAME_TABLE_UTENTI + " gi� esistente");
		}
		try{
			create.execute(DBConstants.CREATE_TABLE_SALE);	
		} catch (SQLException e) {
			System.err.println("Tabella "+ DBConstants.NAME_TABLE_SALE + " gi� esistente");
		}
		try{
			create.execute(DBConstants.CREATE_TABLE_PROGRAMMAZIONE);	
		} catch (SQLException e) {
			System.err.println("Tabella "+ DBConstants.NAME_TABLE_PROGRAMMAZIONE + " gi� esistente");
		}
		try{
			create.execute(DBConstants.CREATE_TABLE_PRENOTAZIONI);	
		} catch (SQLException e) {
			System.err.println("Tabella "+ DBConstants.NAME_TABLE_PRENOTAZIONI + " gi� esistente");
		}
		try{
			create.execute(DBConstants.CREATE_TABLE_CHIAVI);	
		} catch (SQLException e) {
			System.err.println("Tabella "+ DBConstants.NAME_TABLE_CHIAVI + " gi� esistente");
		}
	}
	
	public static synchronized GestoreCinema getGestoreCinema() throws SQLException{
		if(instance==null)
			instance=new GestoreCinema();
		return instance;
	}
	
	// Metodi SALE
	
	public synchronized Sala[] getAllSale() throws SQLException{
		ArrayList<Sala> sale = new ArrayList<Sala>();
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_SALE);
		while(result.next()){
			Sala tmp = new Sala(result.getString("NOME"), result.getInt("CAPIENZA"));
			sale.add(tmp);
		}
		Sala[] tmpsale = new Sala[sale.size()];
		int i=0;
		for(Sala s:sale){
			tmpsale[i++]=s;
		}
		return tmpsale;
	}
	
	public synchronized Sala getSala(String nomeSala) throws SQLException, SalaSconosciutaException{
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_SALE+" WHERE NOME='"+nomeSala+"'");
		if(!result.first())
			throw new SalaSconosciutaException(nomeSala);
		Sala s = new Sala(result.getString("NOME"), result.getInt("CAPIENZA"));
		return s;
	}
	
	//Metodi FILM
	
	public synchronized Film[] getAllFilm() throws SQLException{
		ArrayList<Film> films = new ArrayList<Film>();
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_FILM);
		while(result.next()){
			Film tmp = new Film(result.getString("COD_FILM"), result.getString("NOME"), result.getString("REGISTA"), result.getInt("ANNOUSCITA"));
			films.add(tmp);
		}
		Film[] tmpfilm = new Film[films.size()];
		int i=0;
		for(Film f:films){
			tmpfilm[i++]=f;
		}
		return tmpfilm;
	}
	
	public synchronized Film getFilm(String codFilm) throws SQLException, FilmSconosciutoException{
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_FILM+" WHERE COD_FILM='"+codFilm+"'");
		if(!result.first())
			throw new FilmSconosciutoException(codFilm);
		Film f = new Film(result.getString("COD_FILM"), result.getString("NOME"), result.getString("REGISTA"), result.getInt("ANNOUSCITA"));

		return f;
	}
	
	public synchronized Film addFilm(Film f) throws SQLException, FilmSconosciutoException, FilmDuplicatoException{
		try{
			getFilm(f.getCodFilm());
			throw new FilmDuplicatoException(f.getCodFilm());
		}
		catch(FilmSconosciutoException ex){
			Statement add = con.createStatement();
			add.execute("INSERT INTO "+DBConstants.NAME_TABLE_FILM+" (COD_FILM, NOME, REGISTA, ANNOUSCITA, GENERE) VALUES ('"+
							f.getCodFilm()+"','"+f.getNome()+"','"+f.getRegista()+"',"+f.getAnnoUscita()+"');");
			return getFilm(f.getCodFilm());
		}
	}
	
	
	//METODI PRENOTAZIONE
	
	public synchronized Prenotazione[] getAllPrenotazioni() throws SQLException{
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_PRENOTAZIONI);
		while(result.next()){
			Prenotazione tmp = new Prenotazione(result.getString("COD_PRENOTAZIONE"), result.getString("NOME_UTENTE"), result.getString("COD_PROGRAMMAZIONE"), result.getInt("POSTI_PRENOTATI"));
			prenotazioni.add(tmp);
		}
		Prenotazione[] tmpprenotazioni = new Prenotazione[prenotazioni.size()];
		int i=0;
		for(Prenotazione p:prenotazioni){
			tmpprenotazioni[i++]=p;
		}
		return tmpprenotazioni;
	}
	
	public synchronized Prenotazione getPrenotazione(String codPrenotazione) throws SQLException, PrenotazioneSconosciutaException{
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_PRENOTAZIONI+" WHERE COD_PRENOTAZIONE='"+codPrenotazione+"'");
		if(!result.first())
			throw new PrenotazioneSconosciutaException(codPrenotazione);
		Prenotazione p = new Prenotazione(result.getString("COD_PRENOTAZIONE"), result.getString("NOME_UTENTE"), result.getString("COD_PROGRAMMAZIONE"), result.getInt("POSTI_PRENOTATI"));

		return p;
	}
	
	public synchronized Prenotazione addPrenotazione(Prenotazione p) throws SQLException, PrenotazioneSconosciutaException, ProgrammazioneSconosciutaException, SalaSconosciutaException, PostiTerminatiException, PrenotazioneDuplicataException {
		Programmazione prog = getProgrammazione(p.getCodProgrammazione());
		Sala sala = getSala(prog.getNomeSala());
		int posti= prog.getPostiPrenotati()+p.getPostiPrenotati();
		if(posti<=sala.getCapienza()){
			Statement add = con.createStatement();
			add.execute("INSERT INTO "+DBConstants.NAME_TABLE_PRENOTAZIONI+" (COD_PRENOTAZIONE, NOME_UTENTE, COD_PROGRAMMAZIONE, POSTI_PRENOTATI) VALUES ('"+
					p.getCodPrenotazione()+"','"+p.getNomeUtente()+"','"+p.getCodProgrammazione()+"','"+p.getPostiPrenotati()+"');");
			add.execute("UPDATE "+DBConstants.NAME_TABLE_PROGRAMMAZIONE+" SET POSTI_PRENOTATI='"+posti+"' WHERE COD_PROGRAMMAZIONE='"+prog.getCodProgrammazione()+"';");
			return getPrenotazione(p.getCodPrenotazione());
		} 
		else
			throw new PostiTerminatiException();
	}
	
	public synchronized Prenotazione removePrenotazione(String codice) throws SQLException, PrenotazioneSconosciutaException{
		Prenotazione tmp = getPrenotazione(codice);
		Statement delete = con.createStatement();
		delete.execute("DELETE FROM "+DBConstants.NAME_TABLE_PRENOTAZIONI+" WHERE COD_PRENOTAZIONE='"+tmp.getCodPrenotazione()+"';");
		return tmp;
	}
	
	
	//METODI PROGRAMMAZIONE
	
	public synchronized Programmazione[] getAllProgrammazioni() throws SQLException{
		ArrayList<Programmazione> programmazioni = new ArrayList<Programmazione>();
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_PROGRAMMAZIONE);
		while(result.next()){
			Programmazione tmp = new Programmazione(result.getString("COD_PROGRAMMAZIONE"), result.getString("NOME_SALA"), result.getString("COD_FILM"), result.getString("DATA"), result.getString("ORA"), result.getInt("POSTI_PRENOTATI"));
			programmazioni.add(tmp);
		}
		Programmazione[] tmpprogrammazioni = new Programmazione[programmazioni.size()];
		int i=0;
		for(Programmazione p:programmazioni){
			tmpprogrammazioni[i++]=p;
		}
		return tmpprogrammazioni;
	}
	
	public synchronized Programmazione getProgrammazione(String codProgrammazione) throws SQLException, ProgrammazioneSconosciutaException{
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_PROGRAMMAZIONE+" WHERE COD_PROGRAMMAZIONE='"+codProgrammazione+"'");
		if(!result.first())
			throw new ProgrammazioneSconosciutaException(codProgrammazione);
		Programmazione p = new Programmazione(result.getString("COD_PROGRAMMAZIONE"), result.getString("NOME_SALA"), result.getString("COD_FILM"), result.getString("DATA"), result.getString("ORA"), result.getInt("POSTI_PRENOTATI"));

		return p;
	}
	
	public synchronized Programmazione addProgrammazione(Programmazione p) throws SQLException, ProgrammazioneSconosciutaException, ProgrammazioneDuplicataException{
		try{
			getProgrammazione(p.getCodProgrammazione());
			throw new ProgrammazioneDuplicataException(p.getCodProgrammazione());
		}
		catch(ProgrammazioneSconosciutaException ex){
			Statement add = con.createStatement();
			Date data = p.getData();
			String dataok = new SimpleDateFormat("yyyy-MM-dd").format(data);
			add.execute("INSERT INTO "+DBConstants.NAME_TABLE_PROGRAMMAZIONE+" (COD_PROGRAMMAZIONE, NOME_SALA, COD_FILM, DATA, ORA, POSTI_PRENOTATI) VALUES ('"+
							p.getCodProgrammazione()+"','"+p.getNomeSala()+"','"+p.getCodFilm()+"','"+dataok+"','"+p.getOrario()+"','"+p.getPostiPrenotati()+"');");
			return getProgrammazione(p.getCodProgrammazione());
		}
	}
	
	public synchronized Programmazione removeProgrammazione(String codice) throws SQLException, ProgrammazioneSconosciutaException{
		Programmazione tmp = getProgrammazione(codice);
		Statement delete = con.createStatement();
		delete.execute("DELETE FROM "+DBConstants.NAME_TABLE_PROGRAMMAZIONE+" WHERE COD_PROGRAMMAZIONE='"+tmp.getCodProgrammazione()+"';");
		return tmp;
	}
	
	//METODI UTENTE
	
	
	public synchronized Utente[] getAllUtenti() throws SQLException{
		ArrayList<Utente> utenti = new ArrayList<Utente>();
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_UTENTI);
		while(result.next()){
			Utente tmp = new Utente(result.getString("NOME_UTENTE"), result.getString("NOME"), result.getString("COGNOME"), result.getString("EMAIL"), result.getString("PASSWORD"), result.getBoolean("ADMIN"));
			utenti.add(tmp);
		}
		Utente[] tmputenti = new Utente[utenti.size()];
		int i=0;
		for(Utente u:utenti){
			tmputenti[i++]=u;
		}
		return tmputenti;
	}
	
	public synchronized Utente getUtenteByUsername(String nomeUtente) throws SQLException, UtenteSconosciutoException{
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_UTENTI+" WHERE NOME_UTENTE='"+nomeUtente+"'");
		if(!result.first())
			throw new UtenteSconosciutoException(nomeUtente);
		Utente u = new Utente(result.getString("NOME_UTENTE"), result.getString("NOME"), result.getString("COGNOME"), result.getString("EMAIL"), result.getString("PASSWORD"), result.getBoolean("ADMIN"));

		return u;
	}
	
	public synchronized Utente getUtenteByEmail(String email) throws UtenteSconosciutoException, SQLException{
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_UTENTI+" WHERE EMAIL='"+email+"'");
		if(!result.first())
			throw new UtenteSconosciutoException(email);
		Utente u = new Utente(result.getString("NOME_UTENTE"), result.getString("NOME"), result.getString("COGNOME"), result.getString("EMAIL"), result.getString("PASSWORD"), result.getBoolean("ADMIN"));
		
		return u;
	}
	
	public synchronized Utente getUtenteByChiave(String key) throws SQLException, UtenteSconosciutoException, ChiaveSconosciutaException{
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_CHIAVI+" WHERE CHIAVE_UTENTE='"+key+"'");
		if(!result.first())
			throw new ChiaveSconosciutaException(key);
		String username = result.getString("COD_UTENTE");
		Utente u = getUtenteByUsername(username);
		
		return u;
	}
	
	public synchronized Utente addUtente(Utente u) throws SQLException, UtenteSconosciutoException, UtenteDuplicatoException{
		try{
			getUtenteByUsername(u.getNomeUtente());
			throw new UtenteDuplicatoException(u.getNomeUtente());
		}
		catch(UtenteSconosciutoException ex){
			try {
				getUtenteByEmail(u.getEmail());
			throw new UtenteDuplicatoException(u.getNomeUtente());
			}catch (UtenteSconosciutoException ex2){
			
			Statement add = con.createStatement();
			
			add.execute("INSERT INTO "+DBConstants.NAME_TABLE_UTENTI+" (NOME_UTENTE, NOME, COGNOME, EMAIL, PASSWORD, ADMIN) VALUES ('"+
							u.getNomeUtente()+"','"+u.getNome()+"','"+u.getCognome()+"','"+u.getEmail()+"','"+u.getPassword()+"','"+(u.isAdmin()?"1":"0")+");");
			addChiave(u);
			return getUtenteByUsername(u.getNomeUtente());
			}
		}
	}

	// FILTRI
	
	public synchronized Prenotazione[] filtraPrenotazioniPerUsername(String username) throws SQLException{
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ArrayList<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_PRENOTAZIONI+" WHERE NOME_UTENTE='"+username+"'");
		while(result.next()){
			Prenotazione pr = new Prenotazione(result.getString("COD_PRENOTAZIONE"), result.getString("NOME_UTENTE"), result.getString("COD_PROGRAMMAZIONE"), result.getInt("POSTI_PRENOTATI"));
			prenotazioni.add(pr);
		}
		
		Prenotazione[] tmppren = new Prenotazione[prenotazioni.size()];
		int i=0;
		for(Prenotazione p:prenotazioni){
			tmppren[i++]=p;
		}
		return tmppren;
	}
	
	public synchronized Programmazione[] filtraProgrammazioniPerData(String data) throws SQLException{
		ArrayList<Programmazione> programmazioni = new ArrayList<Programmazione>();
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_PROGRAMMAZIONE+" WHERE DATA='"+data+"'");
		while(result.next()){
			String dataok = new SimpleDateFormat("yyyy-MM-dd").format(result.getDate("DATA"));
			Programmazione prog = new Programmazione(result.getString("COD_PROGRAMMAZIONE"), result.getString("NOME_SALA"),
					 result.getString("COD_FILM"), dataok, result.getString("ORA"),result.getInt("POSTI_PRENOTATI"));	
			programmazioni.add(prog);
		}
		Programmazione[] tmpprog = new Programmazione[programmazioni.size()];
		int i=0;
		for(Programmazione p:programmazioni){
			tmpprog[i++]=p;
		}
		return tmpprog;
	}
		
	public synchronized Programmazione[] filtraProgrammazioniPerFilm(String codFilm) throws SQLException{
		ArrayList<Programmazione> programmazioni = new ArrayList<Programmazione>();
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_PROGRAMMAZIONE+" WHERE CODICE_FILM='"+codFilm+"'");
		while(result.next()){
			String dataok = new SimpleDateFormat("yyyy-MM-dd").format(result.getDate("DATA"));
			Programmazione prog = new Programmazione(result.getString("COD_PROGRAMMAZIONE"), result.getString("NOME_SALA"),
					 result.getString("COD_FILM"), dataok, result.getString("ORA"),result.getInt("POSTI_PRENOTATI"));	
			programmazioni.add(prog);
		}
		Programmazione[] tmpprog = new Programmazione[programmazioni.size()];
		int i=0;
		for(Programmazione p:programmazioni){
			tmpprog[i++]=p;
		}
		return tmpprog;
	}
	
	
	//METODI CHIAVI
	
	public synchronized String getChiaveByUtente(String nomeUtente) throws SQLException, UtenteSconosciutoException{
		Statement query = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet result = query.executeQuery("SELECT * FROM "+DBConstants.NAME_TABLE_CHIAVI+" WHERE COD_UTENTE='"+nomeUtente+"'");
		if(!result.first())
			throw new UtenteSconosciutoException(nomeUtente);
		String s = result.getString("CHIAVE_UTENTE");

		return s;
	}
	
	public synchronized String addChiave(Utente u) throws SQLException{
		
			Statement add = con.createStatement();
			
			String key = Chiave.generaChiave(u);
			add.execute("INSERT INTO "+DBConstants.NAME_TABLE_CHIAVI+" (COD_UTENTE, CHIAVE_UTENTE) VALUES ('"+
					u.getNomeUtente()+"','"+key+"');");
			return key;
			}	
	
	public synchronized String removeChiave(String nomeUtente) throws SQLException, UtenteSconosciutoException{
		String tmp = getChiaveByUtente(nomeUtente);
		Statement delete = con.createStatement();
		delete.execute("DELETE FROM "+DBConstants.NAME_TABLE_CHIAVI+" WHERE COD_UTENTE='"+nomeUtente+"';");
		return tmp;
	}
	
}
